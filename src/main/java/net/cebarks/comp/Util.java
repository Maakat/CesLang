package net.cebarks.comp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class Util {
	public static ByteBuffer fileToBuffer(File file) {
		ByteBuffer buf = ByteBuffer.allocate((int) file.length());

		try {
			InputStream in = new FileInputStream(file);

			int next;

			while ((next = in.read()) != -1)
				buf.put((byte) next);

			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		buf.flip();
		
		return buf;
	}

	public static void bufferToFile(File file, ByteBuffer buf) {
		try {
			OutputStream out = new FileOutputStream(file);
			out.write(buf.array());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ByteBuffer byteListToBuffer(List<Byte> bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (Byte b : bytes)
			baos.write(b);
		return ByteBuffer.wrap(baos.toByteArray());
	}
}
