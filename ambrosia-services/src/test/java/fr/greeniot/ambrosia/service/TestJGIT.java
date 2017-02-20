//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.service;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.junit.Test;

/**
 * @author Bull $Id$
 */
public class TestJGIT {

	@Test
	public void testJGIT1() throws Exception {
		// Création d'un répertoire temporaire contenant la base documentaire
		// Git
		String workingDirectory = "D:/temp/depot-git-test-" + System.currentTimeMillis();
		// FileUtils.deleteQuietly(new File(workingDirectory));
		FileUtils.forceMkdir(new File(workingDirectory));

		// Création de l'objet de base documentaire
		Repository repo = FileRepositoryBuilder.create(new File(workingDirectory, ".git"));
		repo.create();
		Git git = new Git(repo);

		// Création d'un nouveau fichier et ajout dans l'index
		File newFile = new File(workingDirectory, "myNewFile");
		newFile.createNewFile();
		git.add().addFilepattern("myNewFile").call();

		// Maintenant, nous effectuons le commit avec un message
		RevCommit rev = git.commit().setAuthor("gildas", "gildas@example.com").setMessage("My first commit").call();

		System.out.println(rev.getId());
	}

	@Test
	@Deprecated
	public void testJGIT2() throws Exception {
		// NOK ici

		String name = "depot-git-test-" + System.currentTimeMillis();
		String workingDirectory = "D:/temp/" + name;
		// FileUtils.deleteQuietly(new File(workingDirectory));
		FileUtils.forceMkdir(new File(workingDirectory));

		// Création de l'objet de base documentaire
//		Repository repo = FileRepositoryBuilder.create(new File(workingDirectory, ".git"));
//		repo.create();
		Git git = createGIT(new File(workingDirectory), ".git");

		// Création d'un nouveau fichier et ajout dans l'index
		File newFile = new File(workingDirectory, "myNewFile");
		newFile.createNewFile();
		git.add().addFilepattern("myNewFile").call();

		// Maintenant, nous effectuons le commit avec un message
		RevCommit rev = git.commit().setAuthor("gildas", "gildas@example.com").setMessage("My first commit").call();

		System.out.println(rev.getId());
	}

	/**
	 * Creates a bare repository.
	 * 
	 * @param repositoriesFolder
	 * @param name
	 * @return Repository
	 */
	public static Repository createRepository(File repositoriesFolder, String name) {
		Git git = createGIT(repositoriesFolder, name);
		return git.getRepository();
	}

	/**
	 * Creates a bare repository.
	 * 
	 * @param repositoriesFolder
	 * @param name
	 * @return Repository
	 */
	public static Git createGIT(File repositoriesFolder, String name) {
		try {
			return Git.init().setDirectory(new File(repositoriesFolder, name)).setBare(true).call();
		} catch (GitAPIException e) {
			throw new RuntimeException(e);
		}
	}
}
