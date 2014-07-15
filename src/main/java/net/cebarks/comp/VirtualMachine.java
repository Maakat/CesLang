package net.cebarks.comp;

import java.nio.ByteBuffer;
import java.util.Scanner;

import static net.cebarks.comp.SourceCompile.*;

public class VirtualMachine {
	public static void execute(Program program) {
		ByteBuffer buf = program.getProgramBytes();

		while (buf.hasRemaining()) {
			switch (buf.get()) {
			case EXIT_BYTE:
				program.setExitCode(0);
				break;
			case PUT_BYTE:
				byte[] bs = new byte[buf.get()];
				
				for (int i = 0; i < bs.length; i++) {
					bs[i] = buf.get();
				}
				
				System.out.print(new String(bs));
				break;
			case GET_BYTE:
				new Scanner(System.in).nextLine();
				break;
			}
		}
	}
}
