//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author thomas
 */
public class TestJGIT {

	@Test
	public void testJGIT1() throws Exception {
		// Création d'un répertoire temporaire contenant la base documentaire
		// Git
		String workingDirectory = "C:/temp/depot-git-test-" + System.currentTimeMillis();
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
	public void testJGITCompare() throws Exception {
		// Création d'un répertoire temporaire contenant la base documentaire
		// Git
		String workingDirectory = "C:/temp/depot-git-test-" + System.currentTimeMillis();
		// FileUtils.deleteQuietly(new File(workingDirectory));
		FileUtils.forceMkdir(new File(workingDirectory));

		// Création de l'objet de base documentaire
		Repository repo = FileRepositoryBuilder.create(new File(workingDirectory, ".git"));
		repo.create();
		Git git = new Git(repo);

		// Création d'un nouveau fichier et ajout dans l'index
		File newFile = new File(workingDirectory, "myNewFile");
		newFile.createNewFile();
		String init = "{\n\"name\":\"equi1\",\n\"type\":\"IGB\",\n\"id\":\"123\"\n}";
		// take the file and put in it
		FileUtils.writeStringToFile(newFile, init);
		git.add().addFilepattern("myNewFile").call();

		// Maintenant, nous effectuons le commit avec un message
		RevCommit rev1 = git.commit().setAuthor("gildas", "gildas@example.com").setMessage("My first commit").call();
		ObjectId id1 = rev1.getId();
		System.out.println(id1);

		// modify it
		String modifie = "{\n\"name\":\"equi2\",\n\"type\":\"IGB\",\n\"id\":\"123\"\n}";
		FileUtils.writeStringToFile(newFile, modifie);
		// add, commit
		git.add().addFilepattern("myNewFile").call();
		RevCommit rev2 = git.commit().setAuthor("gildas", "gildas@example.com").setMessage("My Second commit").call();
		ObjectId id2 = rev2.getId();
		System.out.println(id2);

		// diff
		// DiffFormatter diffFormatter = new
		// DiffFormatter(DisabledOutputStream.INSTANCE);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// DiffFormatter diffFormatter = new DiffFormatter(System.out);
		// diffFormatter.setRepository(git.getRepository());
		// List<DiffEntry> entries = diffFormatter.scan(id1, id2);
		//
		// // diff
		// for (DiffEntry entry : entries) {
		//// System.out.println(entry.getChangeType());
		//// System.out.println(entry.getOldPath());
		//// System.out.println(entry.getNewPath());
		// FileHeader fh = diffFormatter.toFileHeader(entry);
		// System.out.println(new String(fh.getBuffer()));
		// }

		// AbstractTreeIterator oldTreeParser = prepareTreeParser(repo,
		// "b97b184b0ce11c0b6a4dcc2b57768ff155cb696b");
		// AbstractTreeIterator newTreeParser = prepareTreeParser(repo,
		// "9e0719d7d773b41b49ebf04e6fd7b5c637e96063");
		AbstractTreeIterator oldTreeParser = prepareTreeParser(repo, id1.getName());
		AbstractTreeIterator newTreeParser = prepareTreeParser(repo, id2.getName());

		// then the porcelain diff-command returns a list of diff entries
		List<DiffEntry> diff = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser)
				// .setPathFilter(PathFilter.create("README.md")).
				// to filter on Suffix use the following instead
				// setPathFilter(PathSuffixFilter.create(".java")).
				.call();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		DiffFormatter formatter = new DiffFormatter(out);

		for (DiffEntry entry : diff) {
			System.out.println("Entry: " + entry + ", from: " + entry.getOldId() + ", to: " + entry.getNewId());
			formatter.setRepository(repo);
			formatter.format(entry);
		}

		String res = new String(out.toByteArray());
		System.out.println("---------------- res");
		System.out.println(res);

		out.close();
		// TODO simple parse string

		// res de type :
		// diff --git a/myNewFile b/myNewFile
		// index 3bdb50a..ada4457 100644
		// --- a/myNewFile
		// +++ b/myNewFile
		// @@ -1,5 +1,5 @@
		// {
		// -"name":"equi1",
		// +"name":"equi2",
		// "type":"IGB",
		// "id":"123"
		// }
		// \ No newline at end of file
		//
	}

	// https://github.com/centic9/jgit-cookbook/blob/master/src/main/java/org/dstadler/jgit/porcelain/ShowFileDiff.java
	private static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId) throws IOException {
		// from the commit we can build the tree which allows us to construct
		// the TreeParser
		// noinspection Duplicates
		try (RevWalk walk = new RevWalk(repository)) {
			RevCommit commit = walk.parseCommit(ObjectId.fromString(objectId));
			RevTree tree = walk.parseTree(commit.getTree().getId());

			CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
			try (ObjectReader oldReader = repository.newObjectReader()) {
				oldTreeParser.reset(oldReader, tree.getId());
			}

			walk.dispose();

			return oldTreeParser;
		}
	}

	@Test
	@Deprecated
	@Ignore
	public void testJGIT2() throws Exception {
		// NOK ici

		String name = "depot-git-test-" + System.currentTimeMillis();
		String workingDirectory = "C:/temp/" + name;
		// FileUtils.deleteQuietly(new File(workingDirectory));
		FileUtils.forceMkdir(new File(workingDirectory));

		// Création de l'objet de base documentaire
		// Repository repo = FileRepositoryBuilder.create(new
		// File(workingDirectory, ".git"));
		// repo.create();
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
