// MQTT with temperature sensor
// I2C with LM75A
// LM75 pin 5 SDA
// LM74 pin 4 SCL
// LM75 has pins A0=A1=A2=GND, WP=GND, write enabled

// SDA, SCL
// ESP-01   0,2
// ESP01 1,3 RX(SDA) TX(SCL) GPIO2 (I2C power)

// ESP-12 default to pins 4(SDA) and 5(SCL).
// KLUDGE : ESP-12 inverted pins 5(SDA) and 4(SCL).
// Pullups de 4.7k

#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <Wire.h>

// #define DEBUG(CODE) CODE
// #define RELEASE(CODE) /* empty */

#define DEBUG(CODE)  /* empty */
#define RELEASE(CODE) CODE

#define PRINT(MESSAGE) DEBUG(Serial.print(MESSAGE))
#define PRINTLN(MESSAGE) DEBUG(Serial.println(MESSAGE))
#define PRINT_VAR(VARIABLE) PRINT(#VARIABLE);PRINTLN(VARIABLE)

// parameters
const int i2cPort = 0x4F;
const long msgPeriod = 60000;
const long ledOnTime = 50;
const long ledOffTime = 15000;
#define wifi_ssid "ssid e" // to change
#define wifi_password "password" // to change
#define mqtt_server "myServer" // to change serverName simple
#define MQTT_PORT 1883
#define mqtt_user "compteBroker"  //s'il a été configuré sur Mosquitto
#define mqtt_password "duCompte" //idem

#define temperature_topic "temperature"  //Topic température
#define minute_topic "temperature"        //Topic minute

class LM75A {
  public:
    LM75A( uint8_t devI2CAddress );
    void init( void );
    float get_temp( void );
  private:
    uint8_t my_dev_addr;
};

LM75A::LM75A( uint8_t passed_i2c_addr )
{
  my_dev_addr = passed_i2c_addr;
}

void
LM75A::init( void )
{
  Wire.beginTransmission(i2cPort);  // transmit to device
  Wire.write(1);          // set Conf pointer
  Wire.write(4);          // default configuration + LOW when hot
  // Wire.write(0);         // default configuration + HIGH when hot
  Wire.endTransmission();   // stop transmitting
}

float
LM75A::get_temp( void )
{
  float temp;         // var contains tempdata for easy reading by human beings ;)
  Wire.beginTransmission(i2cPort);
  Wire.write(0x00);  // 0 = 'temperature register' and this implies that we expect 2 bytes (msb, lsb) in return to our request
  Wire.endTransmission();

  Wire.requestFrom((uint8_t)i2cPort, (uint8_t)2);  // read 2 bytes
  int w = 100;
  while ((Wire.available() < 2) && (0 < w--)) {
    //Serial.print(".");
  }  // note the null loop!

  word regdata = (Wire.read() << 8) | Wire.read();
  temp = ((float)(((int16_t)regdata) >> 5)) / 8;
  return temp;
}
bool led;

double temperature;
LM75A lm75a(i2cPort);

//Buffer qui permet de décoder les messages MQTT reçus
char message_buff[100];

long lastMsg = 0;   //Horodatage du dernier message publié sur MQTT
long lastLed = 0;   //Horodatage de la led
int cp = 0;

//Création des objets
WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  Serial.begin(115200);
  pinMode(2, OUTPUT);
  digitalWrite(2, HIGH);
  pinMode(LED_BUILTIN, OUTPUT);
  setup_wifi();           //On se connecte au réseau wifi
  client.setServer(mqtt_server, MQTT_PORT);    //Configuration de la connexion au serveur MQTT
  //   client.setCallback(callback);  //La fonction de callback qui est executée à chaque réception de message
  Wire.begin(5, 4); // on ESP-12
  lm75a.init();
}

//Connexion au réseau WiFi
void setup_wifi() {
  delay(10);
  WiFi.mode(WIFI_STA);
  WiFi.begin(wifi_ssid, wifi_password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
}

//Reconnexion
void reconnect() {
  if (!client.connected()) {
    PRINTLN("Connexion au serveur MQTT...");
  //  client.connect("ESP8266Client", mqtt_user, mqtt_password);
    client.connect("ESP8266Client");
  }
}

void loop() {
  client.loop();
  long now = millis();
  if (now - lastMsg > msgPeriod) { // set to 3 seconds if (!client.connected()) {
    reconnect();
    lastMsg = now;
    temperature = lm75a.get_temp();
    Serial.print("temperature : ");
    Serial.println(temperature);
    // float h = cp++;
    long h = now / 60000;
    client.publish(temperature_topic, String(temperature).c_str(), true);   // Publie la température sur le topic temperature_topic
    client.publish(minute_topic, String(h).c_str(), true);      // Et la minute
  }
  if (led) {
    if (now - lastLed > ledOnTime) {
      lastLed = now;
      led = false;
      digitalWrite(LED_BUILTIN, true);
    }
  } else {
    if (now - lastLed > ledOffTime) {
      lastLed = now;
      led = true;
      digitalWrite(LED_BUILTIN, false);
    }
  }
}


