package net.cebarks.comp;

import java.io.File;
import java.nio.ByteBuffer;

public class Program {
	private final ByteBuffer programBytes;

	private int exitCode;

	public Program(ByteBuffer program) {
		this.programBytes = program;
	}

	public Program(File file) {
		this(Util.fileToBuffer(file));
	}

	public ByteBuffer getProgramBytes() {
		return programBytes;
	}

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int newExitCode) {
		this.exitCode = newExitCode;
	}
}
