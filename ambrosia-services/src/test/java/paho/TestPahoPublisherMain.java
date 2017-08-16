/**
 * 
 */
package paho;

/**
 * 
 * @author anaspleen
 *
 */
public class TestPahoPublisherMain {

	public static void main(String[] args) {
		// MQTT TCA VM
		// MQTTUtils.publishMessagesInThreads("TCA/ex",
		// "tcp://192.168.1.83:1883", "JavaSample", 10, 10000);
		// MQTTUtils.publishMessagesInThreads("TCA/ex",
		// "tcp://192.168.1.83:1883", "JavaSample", 5, 1000);

		// MQTT Marco
		MQTTUtils.publishMessagesInThreads("marco", "tcp://192.168.1.49:1883", "JavaSample", 1000, 1000);

		// conf load balancer nginx :
		//
		// /etc/nginx/stream_conf.d
		// root@debian:/etc/nginx/stream_conf.d# cat mqqt.conf
		// # begin tca
		// #log_format mqtt '$remote_addr [$time_local] $protocol $status
		// $bytes_received '
		// # '$bytes_sent $upstream_addr';
		//
		// #access_log /var/log/nginx/mqtt_access.log mqtt buffer=32k;
		//
		// upstream hive_mq {
		// server 127.0.0.1:1883; #node1
		// server 192.168.1.49:18832; #node2
		// # server 127.0.0.1:18833; #node3
		// zone tcp_mem 64k;
		// }
		//
		// server {
		// access_log /var/log/nginx/mqtt_access.log mqtt buffer=32k; => marche
		// pas en stretch
		// listen 18833;
		// proxy_pass hive_mq;
		// proxy_connect_timeout 1s;
		//
		//
		// # access_log /var/log/nginx/mqtt_accesss.log '$remote_addr';
		// # log_file /var/log/nginx/mqtt_access.log mqtt;
		// }
		//
		// # end tca

		// root@debian:/etc/nginx/stream_conf.d# cat
		// stream_mqtt_healthcheck.conf.init
		// log_format mqtt '$remote_addr [$time_local] $protocol $status';

		// dans nginx.conf
		// # tca
		// stream {
		// include stream_conf.d/*.conf;
		// }

	}
}
