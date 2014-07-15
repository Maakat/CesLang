package net.cebarks.comp;

import java.io.File;
import java.nio.ByteBuffer;

public class App {

	public static void main(String[] args) throws Exception {
		if (args[0].equalsIgnoreCase("-c") || args[0].equalsIgnoreCase("--compile")) {
			SourceCompile compiler = new SourceCompile(args[1]);
			ByteBuffer compiledSource = compiler.compile();
			System.out.println("Compiled file!");
			Util.bufferToFile(new File(args[1].replace(".ces", ".ceb")), compiledSource);
			System.exit(0);
		}

		Program program = new Program(new File(args[0]));
		File programFile = new File(args[0]);
		System.out.println(programFile.getAbsolutePath());

		VirtualMachine.execute(new Program(Util.fileToBuffer(programFile)));

		System.exit(program.getExitCode());
	}
}
