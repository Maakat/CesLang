package net.cebarks.comp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SourceCompile {

	private final File fileToCompile;

	public static final byte EXIT_BYTE = 0x00;
	public static final byte PUT_BYTE = 0x01;
	public static final byte GET_BYTE = 0x02;
	public static final byte GOTO_BYTE = 0x03;
	public static final byte TYPE_BYTE = 0x04;
	public static final byte END_BYTE = 0x05;

	public SourceCompile(File file) {
		this.fileToCompile = file;
	}

	public SourceCompile(String fileName) {
		this(new File(fileName));
	}

	public ByteBuffer compile() throws IOException, FileNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Scanner in = new Scanner(fileToCompile);

		System.out.println("Compiling file: \"" + fileToCompile.getName() + "\"...");

		while (in.hasNext()) {
			String line = in.nextLine();

			if (line.equals("")) {
				System.out.println("");
				continue;
			}

			if (line.startsWith("exit")) {
				System.out.println("exit");
				baos.write(EXIT_BYTE);
			}

			if (line.startsWith("put")) {
				System.out.println("put");
				String s = line.substring(4);
				baos.write(PUT_BYTE);
				baos.write((byte) s.getBytes().length);
				baos.write(s.getBytes(Charset.forName("UTF-8")));
			}

			if (line.startsWith("get")) {
				System.out.println("get");
				baos.write(GET_BYTE);
			}
		}

		byte[] bytes = baos.toByteArray();

		if (bytes[bytes.length - 1] != EXIT_BYTE) {
			baos.write(EXIT_BYTE);
		}

		in.close();

		return ByteBuffer.wrap(baos.toByteArray());
	}
}
