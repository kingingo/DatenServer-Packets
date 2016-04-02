package dev.wolveringer.dataserver.protocoll;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.UUID;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.Unpooled;

public class DataBuffer extends ByteBuf {
	private ByteBuf handle;

	public DataBuffer(DataInputStream stream) throws IOException {
		handle = Unpooled.buffer(stream.available());
		
		byte[] buffer = new byte[8192];
		int bytesRead;
		while ((bytesRead = stream.read(buffer)) != -1) {
			handle.writeBytes(buffer, 0, bytesRead);
		}
	}

	public DataBuffer() {
		handle = Unpooled.buffer();
	}

	public DataBuffer(byte[] data) {
		handle = Unpooled.copiedBuffer(data);
	}

	public String readString() {
		int length = readInt();
		if (length == -1)
			return null;
		byte[] buffer = new byte[length];
		readBytes(buffer);
		return new String(buffer, 0, buffer.length, Charset.forName("UTF-8"));
	}

	public DataBuffer writeString(String s) {
		if (s != null) {
			writeInt(s.getBytes(Charset.forName("UTF-8")).length);
			writeBytes(s.getBytes(Charset.forName("UTF-8")));
		} else
			writeInt(-1);
		return this;
	}

	public UUID readUUID() {
		return new UUID(readLong(), readLong());
	}

	public DataBuffer writeUUID(UUID uuid) {
		writeLong(uuid.getMostSignificantBits());
		writeLong(uuid.getLeastSignificantBits());
		return this;
	}

	public ByteBufAllocator alloc() {
		return handle.alloc();
	}

	public byte[] array() {
		return handle.array();
	}

	public int arrayOffset() {
		return handle.arrayOffset();
	}

	public int bytesBefore(byte arg0) {
		return handle.bytesBefore(arg0);
	}

	public int bytesBefore(int arg0, byte arg1) {
		return handle.bytesBefore(arg0, arg1);
	}

	public int bytesBefore(int arg0, int arg1, byte arg2) {
		return handle.bytesBefore(arg0, arg1, arg2);
	}

	public int capacity() {
		return handle.capacity();
	}

	public ByteBuf capacity(int arg0) {
		return handle.capacity(arg0);
	}

	public ByteBuf clear() {
		return handle.clear();
	}

	public int compareTo(ByteBuf arg0) {
		return handle.compareTo(arg0);
	}

	public ByteBuf copy() {
		return handle.copy();
	}

	public ByteBuf copy(int arg0, int arg1) {
		return handle.copy(arg0, arg1);
	}

	public ByteBuf discardReadBytes() {
		return handle.discardReadBytes();
	}

	public ByteBuf discardSomeReadBytes() {
		return handle.discardSomeReadBytes();
	}

	public ByteBuf duplicate() {
		return handle.duplicate();
	}

	public int ensureWritable(int arg0, boolean arg1) {
		return handle.ensureWritable(arg0, arg1);
	}

	public ByteBuf ensureWritable(int arg0) {
		return handle.ensureWritable(arg0);
	}

	public boolean equals(Object arg0) {
		return handle.equals(arg0);
	}

	public int forEachByte(ByteBufProcessor arg0) {
		return handle.forEachByte(arg0);
	}

	public int forEachByte(int arg0, int arg1, ByteBufProcessor arg2) {
		return handle.forEachByte(arg0, arg1, arg2);
	}

	public int forEachByteDesc(ByteBufProcessor arg0) {
		return handle.forEachByteDesc(arg0);
	}

	public int forEachByteDesc(int arg0, int arg1, ByteBufProcessor arg2) {
		return handle.forEachByteDesc(arg0, arg1, arg2);
	}

	public boolean getBoolean(int arg0) {
		return handle.getBoolean(arg0);
	}

	public byte getByte(int arg0) {
		return handle.getByte(arg0);
	}

	public ByteBuf getBytes(int arg0, byte[] arg1, int arg2, int arg3) {
		return handle.getBytes(arg0, arg1, arg2, arg3);
	}

	public ByteBuf getBytes(int arg0, byte[] arg1) {
		return handle.getBytes(arg0, arg1);
	}

	public ByteBuf getBytes(int arg0, ByteBuf arg1, int arg2, int arg3) {
		return handle.getBytes(arg0, arg1, arg2, arg3);
	}

	public ByteBuf getBytes(int arg0, ByteBuf arg1, int arg2) {
		return handle.getBytes(arg0, arg1, arg2);
	}

	public ByteBuf getBytes(int arg0, ByteBuf arg1) {
		return handle.getBytes(arg0, arg1);
	}

	public ByteBuf getBytes(int arg0, ByteBuffer arg1) {
		return handle.getBytes(arg0, arg1);
	}

	public int getBytes(int arg0, GatheringByteChannel arg1, int arg2) throws IOException {
		return handle.getBytes(arg0, arg1, arg2);
	}

	public ByteBuf getBytes(int arg0, OutputStream arg1, int arg2) throws IOException {
		return handle.getBytes(arg0, arg1, arg2);
	}

	public char getChar(int arg0) {
		return handle.getChar(arg0);
	}

	public double getDouble(int arg0) {
		return handle.getDouble(arg0);
	}

	public float getFloat(int arg0) {
		return handle.getFloat(arg0);
	}

	public int getInt(int arg0) {
		return handle.getInt(arg0);
	}

	public long getLong(int arg0) {
		return handle.getLong(arg0);
	}

	public int getMedium(int arg0) {
		return handle.getMedium(arg0);
	}

	public short getShort(int arg0) {
		return handle.getShort(arg0);
	}

	public short getUnsignedByte(int arg0) {
		return handle.getUnsignedByte(arg0);
	}

	public long getUnsignedInt(int arg0) {
		return handle.getUnsignedInt(arg0);
	}

	public int getUnsignedMedium(int arg0) {
		return handle.getUnsignedMedium(arg0);
	}

	public int getUnsignedShort(int arg0) {
		return handle.getUnsignedShort(arg0);
	}

	public boolean hasArray() {
		return handle.hasArray();
	}

	public boolean hasMemoryAddress() {
		return handle.hasMemoryAddress();
	}

	public int hashCode() {
		return handle.hashCode();
	}

	public int indexOf(int arg0, int arg1, byte arg2) {
		return handle.indexOf(arg0, arg1, arg2);
	}

	public ByteBuffer internalNioBuffer(int arg0, int arg1) {
		return handle.internalNioBuffer(arg0, arg1);
	}

	public boolean isDirect() {
		return handle.isDirect();
	}

	public boolean isReadable() {
		return handle.isReadable();
	}

	public boolean isReadable(int arg0) {
		return handle.isReadable(arg0);
	}

	public boolean isWritable() {
		return handle.isWritable();
	}

	public boolean isWritable(int arg0) {
		return handle.isWritable(arg0);
	}

	public ByteBuf markReaderIndex() {
		return handle.markReaderIndex();
	}

	public ByteBuf markWriterIndex() {
		return handle.markWriterIndex();
	}

	public int maxCapacity() {
		return handle.maxCapacity();
	}

	public int maxWritableBytes() {
		return handle.maxWritableBytes();
	}

	public long memoryAddress() {
		return handle.memoryAddress();
	}

	public ByteBuffer nioBuffer() {
		return handle.nioBuffer();
	}

	public ByteBuffer nioBuffer(int arg0, int arg1) {
		return handle.nioBuffer(arg0, arg1);
	}

	public int nioBufferCount() {
		return handle.nioBufferCount();
	}

	public ByteBuffer[] nioBuffers() {
		return handle.nioBuffers();
	}

	public ByteBuffer[] nioBuffers(int arg0, int arg1) {
		return handle.nioBuffers(arg0, arg1);
	}

	public ByteOrder order() {
		return handle.order();
	}

	public ByteBuf order(ByteOrder arg0) {
		return handle.order(arg0);
	}

	public boolean readBoolean() {
		return handle.readBoolean();
	}

	public byte readByte() {
		return handle.readByte();
	}

	public ByteBuf readBytes(byte[] arg0, int arg1, int arg2) {
		return handle.readBytes(arg0, arg1, arg2);
	}

	public ByteBuf readBytes(byte[] arg0) {
		return handle.readBytes(arg0);
	}

	public ByteBuf readBytes(ByteBuf arg0, int arg1, int arg2) {
		return handle.readBytes(arg0, arg1, arg2);
	}

	public ByteBuf readBytes(ByteBuf arg0, int arg1) {
		return handle.readBytes(arg0, arg1);
	}

	public ByteBuf readBytes(ByteBuf arg0) {
		return handle.readBytes(arg0);
	}

	public ByteBuf readBytes(ByteBuffer arg0) {
		return handle.readBytes(arg0);
	}

	public int readBytes(GatheringByteChannel arg0, int arg1) throws IOException {
		return handle.readBytes(arg0, arg1);
	}

	public ByteBuf readBytes(int arg0) {
		return handle.readBytes(arg0);
	}

	public ByteBuf readBytes(OutputStream arg0, int arg1) throws IOException {
		return handle.readBytes(arg0, arg1);
	}

	public char readChar() {
		return handle.readChar();
	}

	public double readDouble() {
		return handle.readDouble();
	}

	public float readFloat() {
		return handle.readFloat();
	}

	public int readInt() {
		return handle.readInt();
	}

	public long readLong() {
		return handle.readLong();
	}

	public int readMedium() {
		return handle.readMedium();
	}

	public short readShort() {
		return handle.readShort();
	}

	public ByteBuf readSlice(int arg0) {
		return handle.readSlice(arg0);
	}

	public short readUnsignedByte() {
		return handle.readUnsignedByte();
	}

	public long readUnsignedInt() {
		return handle.readUnsignedInt();
	}

	public int readUnsignedMedium() {
		return handle.readUnsignedMedium();
	}

	public int readUnsignedShort() {
		return handle.readUnsignedShort();
	}

	public int readableBytes() {
		return handle.readableBytes();
	}

	public int readerIndex() {
		return handle.readerIndex();
	}

	public ByteBuf readerIndex(int arg0) {
		return handle.readerIndex(arg0);
	}

	public int refCnt() {
		return handle.refCnt();
	}

	public boolean release() {
		return handle.release();
	}

	public boolean release(int arg0) {
		return handle.release(arg0);
	}

	public ByteBuf resetReaderIndex() {
		return handle.resetReaderIndex();
	}

	public ByteBuf resetWriterIndex() {
		return handle.resetWriterIndex();
	}

	public ByteBuf retain() {
		return handle.retain();
	}

	public ByteBuf retain(int arg0) {
		return handle.retain(arg0);
	}

	public ByteBuf setBoolean(int arg0, boolean arg1) {
		return handle.setBoolean(arg0, arg1);
	}

	public ByteBuf setByte(int arg0, int arg1) {
		return handle.setByte(arg0, arg1);
	}

	public ByteBuf setBytes(int arg0, byte[] arg1, int arg2, int arg3) {
		return handle.setBytes(arg0, arg1, arg2, arg3);
	}

	public ByteBuf setBytes(int arg0, byte[] arg1) {
		return handle.setBytes(arg0, arg1);
	}

	public ByteBuf setBytes(int arg0, ByteBuf arg1, int arg2, int arg3) {
		return handle.setBytes(arg0, arg1, arg2, arg3);
	}

	public ByteBuf setBytes(int arg0, ByteBuf arg1, int arg2) {
		return handle.setBytes(arg0, arg1, arg2);
	}

	public ByteBuf setBytes(int arg0, ByteBuf arg1) {
		return handle.setBytes(arg0, arg1);
	}

	public ByteBuf setBytes(int arg0, ByteBuffer arg1) {
		return handle.setBytes(arg0, arg1);
	}

	public int setBytes(int arg0, InputStream arg1, int arg2) throws IOException {
		return handle.setBytes(arg0, arg1, arg2);
	}

	public int setBytes(int arg0, ScatteringByteChannel arg1, int arg2) throws IOException {
		return handle.setBytes(arg0, arg1, arg2);
	}

	public ByteBuf setChar(int arg0, int arg1) {
		return handle.setChar(arg0, arg1);
	}

	public ByteBuf setDouble(int arg0, double arg1) {
		return handle.setDouble(arg0, arg1);
	}

	public ByteBuf setFloat(int arg0, float arg1) {
		return handle.setFloat(arg0, arg1);
	}

	public ByteBuf setIndex(int arg0, int arg1) {
		return handle.setIndex(arg0, arg1);
	}

	public ByteBuf setInt(int arg0, int arg1) {
		return handle.setInt(arg0, arg1);
	}

	public ByteBuf setLong(int arg0, long arg1) {
		return handle.setLong(arg0, arg1);
	}

	public ByteBuf setMedium(int arg0, int arg1) {
		return handle.setMedium(arg0, arg1);
	}

	public ByteBuf setShort(int arg0, int arg1) {
		return handle.setShort(arg0, arg1);
	}

	public ByteBuf setZero(int arg0, int arg1) {
		return handle.setZero(arg0, arg1);
	}

	public ByteBuf skipBytes(int arg0) {
		return handle.skipBytes(arg0);
	}

	public ByteBuf slice() {
		return handle.slice();
	}

	public ByteBuf slice(int arg0, int arg1) {
		return handle.slice(arg0, arg1);
	}

	public String toString() {
		return handle.toString();
	}

	public String toString(Charset arg0) {
		return handle.toString(arg0);
	}

	public String toString(int arg0, int arg1, Charset arg2) {
		return handle.toString(arg0, arg1, arg2);
	}

	public ByteBuf unwrap() {
		return handle.unwrap();
	}

	public int writableBytes() {
		return handle.writableBytes();
	}

	public DataBuffer writeBoolean(boolean arg0) {
		handle.writeBoolean(arg0);
		return this;
	}

	public DataBuffer writeByte(int arg0) {
		handle.writeByte(arg0);
		return this;
	}

	public ByteBuf writeBytes(byte[] arg0, int arg1, int arg2) {
		return handle.writeBytes(arg0, arg1, arg2);
	}

	public ByteBuf writeBytes(byte[] arg0) {
		return handle.writeBytes(arg0);
	}

	public ByteBuf writeBytes(ByteBuf arg0, int arg1, int arg2) {
		return handle.writeBytes(arg0, arg1, arg2);
	}

	public ByteBuf writeBytes(ByteBuf arg0, int arg1) {
		return handle.writeBytes(arg0, arg1);
	}

	public ByteBuf writeBytes(ByteBuf arg0) {
		return handle.writeBytes(arg0);
	}

	public ByteBuf writeBytes(ByteBuffer arg0) {
		return handle.writeBytes(arg0);
	}

	public int writeBytes(InputStream arg0, int arg1) throws IOException {
		return handle.writeBytes(arg0, arg1);
	}

	public int writeBytes(ScatteringByteChannel arg0, int arg1) throws IOException {
		return handle.writeBytes(arg0, arg1);
	}

	public ByteBuf writeChar(int arg0) {
		return handle.writeChar(arg0);
	}

	public ByteBuf writeDouble(double arg0) {
		return handle.writeDouble(arg0);
	}

	public ByteBuf writeFloat(float arg0) {
		return handle.writeFloat(arg0);
	}

	public DataBuffer writeInt(int arg0) {
		handle.writeInt(arg0);
		return this;
	}

	public ByteBuf writeLong(long arg0) {
		return handle.writeLong(arg0);
	}

	public ByteBuf writeMedium(int arg0) {
		return handle.writeMedium(arg0);
	}

	public ByteBuf writeShort(int arg0) {
		return handle.writeShort(arg0);
	}

	public ByteBuf writeZero(int arg0) {
		return handle.writeZero(arg0);
	}

	public int writerIndex() {
		return handle.writerIndex();
	}

	public ByteBuf writerIndex(int arg0) {
		return handle.writerIndex(arg0);
	}
}
