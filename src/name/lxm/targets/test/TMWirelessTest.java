/**
 * 
 */
package name.lxm.targets.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import name.lxm.targets.model.TargetEntity;
import name.lxm.targets.wireless.TMWireless;
import name.lxm.targets.wireless.data.CommandFrame;
import name.lxm.targets.wireless.data.InitResponseFrame;
import name.lxm.targets.wireless.data.ResponseFrame;

/**
 * @author Xiaoming Li Mar 22, 2017
 *
 */
public class TMWirelessTest {

	/**
	 * Test method for {@link name.lxm.targets.wireless.TMWireless#sendInitResetCommand()}.
	 */
	@Test
	public void testSendInitResetCommand() {
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[]{0x00, 0x00});
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TMWireless tw = new TMWireless(bais, baos);
		try {
			tw.sendInitResetCommand();
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		byte[] resq = new byte[]{0x7E, 0x00, 0x20, 0x10, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, /*0~9*/
				0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 0x55, 0x01, 0x00, /*10~19*/
				0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0xff, (byte) 0xff, 0x00, 0x00, 0x01, /*20-29*/
				0x02, 0x03, 0x04, 0x05, 0x06, 0x5B}; /*30-36*/
		byte[] ans = baos.toByteArray();
		assertArrayEquals(Arrays.copyOfRange(resq, 0, 17), Arrays.copyOfRange(ans, 0, 17));
		assertArrayEquals(Arrays.copyOfRange(resq, 17, 29), Arrays.copyOfRange(ans, 17, 29));
	}

	/**
	 * Test method for {@link name.lxm.targets.wireless.TMWireless#readResponseFrame()}.
	 */
	@Test
	public void testReadResponseFrame() {
		byte[] resp = new byte[]{
				0x7E, 0x00, 0x4C, (byte) 0x90, 0x00, 0x13, (byte) 0xA2, 0x00, 0x41, 0x53, 
				(byte) 0xBA, 0x5E, (byte) 0xD0, 0x77, 0x01, 0x01, 0x30, 0x30, 0x31, 0x33,
				0x41, 0x32, 0x30, 0x30, 0x34, 0x31, 0x35, 0x33, 0x42, 0x41,
				0x35, 0x45, 0x56, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x30, 0x30, 0x30, (byte) 0xff, (byte) 0xff, 0x04, 0x42, 0x01,
				0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x24
		};
		
		ResponseFrame fs = new ResponseFrame(resp);
		assertEquals("Frametype is not matched.", fs.getFrameType(), InitResponseFrame.FRAME_TYPE);
		
	}

	/**
	 * Test method for {@link name.lxm.targets.wireless.TMWireless#sendConfigCommand(name.lxm.targets.model.TargetEntity)}.
	 */
	@Test
	public void testSendConfigCommand() {
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[]{0x00, 0x00});
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TMWireless tw = new TMWireless(bais, baos);
		try {
			TargetEntity te = new TargetEntity(0, 1, new byte[]{0,0,0,0,0,0,(byte) 0xff,(byte) 0xff}, 0, System.currentTimeMillis());
			te.setTimestamp(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06});
			tw.sendConfigCommand(te);;
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		byte[] resq = new byte[]{0x7E, 0x00, 0x20, 0x10, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 
				0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 0x55, 0x02, 0x00, 
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 
				0x02, 0x03, 0x04, 0x05, 0x06, 0x5A}; 
		byte[] ans = baos.toByteArray();
		assertArrayEquals(Arrays.copyOfRange(resq, 0, 17), Arrays.copyOfRange(ans, 0, 17));
		assertArrayEquals(Arrays.copyOfRange(resq, 17, 29), Arrays.copyOfRange(ans, 17, 29));
	}

	/**
	 * Test method for {@link name.lxm.targets.wireless.TMWireless#sendStatusCommand()}.
	 */
	@Test
	public void testSendStatusCommand() {
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[]{0x00, 0x00});
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TMWireless tw = new TMWireless(bais, baos);
		try {
			tw.sendStatusCommand();
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		byte[] resq = new byte[]{0x7E, 0x00, 0x20, 0x10, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 0x55, 0x06, 0x00, 
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01,
				0x02, 0x03, 0x04, 0x05, 0x06, 0x56}; 
		byte[] ans = baos.toByteArray();
		assertArrayEquals(Arrays.copyOfRange(resq, 0, 17), Arrays.copyOfRange(ans, 0, 17));
		assertArrayEquals(Arrays.copyOfRange(resq, 17, 29), Arrays.copyOfRange(ans, 17, 29));
	}

	/**
	 * Test method for {@link name.lxm.targets.wireless.TMWireless#sendSingleOPCommand(name.lxm.targets.model.TargetEntity, byte)}.
	 */
	@Test
	public void testSendSingleOPCommand() {
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[]{0x00, 0x00});
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TMWireless tw = new TMWireless(bais, baos);
		try {
			TargetEntity te = new TargetEntity(0, 1, new byte[]{0,0,0,0,0,0,(byte) 0xff,(byte) 0xff}, 0, System.currentTimeMillis());
			//te.setTimestamp(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06});
			tw.sendSingleOPCommand(te, CommandFrame.CMDCODE_LIGHT_ON);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		byte[] resq = new byte[]{0x7E, 0x00, 0x20, 0x10, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 0x55, 0x05, 0x00, 
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01,
				0x02, 0x03, 0x04, 0x05, 0x06, 0x57}; 
		byte[] ans = baos.toByteArray();
		assertArrayEquals(Arrays.copyOfRange(resq, 0, 17), Arrays.copyOfRange(ans, 0, 17));
		assertArrayEquals(Arrays.copyOfRange(resq, 17, 29), Arrays.copyOfRange(ans, 17, 29));
		
		baos.reset();
		
		try {
			TargetEntity te = new TargetEntity(0, 1, new byte[]{0,0,0,0,0,0,(byte) 0xff,(byte) 0xff}, 0, System.currentTimeMillis());
			//te.setTimestamp(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06});
			tw.sendSingleOPCommand(te, CommandFrame.CMDCODE_TARGET_UP);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		resq = new byte[]{0x7E, 0x00, 0x20, 0x10, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 0x55, 0x03, 0x00, 
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01,
				0x02, 0x03, 0x04, 0x05, 0x06, 0x57}; 
		ans = baos.toByteArray();
		assertArrayEquals(Arrays.copyOfRange(resq, 0, 17), Arrays.copyOfRange(ans, 0, 17));
		assertArrayEquals(Arrays.copyOfRange(resq, 17, 29), Arrays.copyOfRange(ans, 17, 29));		
		
		baos.reset();
		
		try {
			TargetEntity te = new TargetEntity(0, 1, new byte[]{0,0,0,0,0,0,(byte) 0xff,(byte) 0xff}, 0, System.currentTimeMillis());
			//te.setTimestamp(new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06});
			tw.sendSingleOPCommand(te, CommandFrame.CMDCODE_TARGET_DOWN);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		resq = new byte[]{0x7E, 0x00, 0x20, 0x10, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFE, 0x00, 0x00, 0x55, 0x04, 0x00, 
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01,
				0x02, 0x03, 0x04, 0x05, 0x06, 0x57}; 
		ans = baos.toByteArray();
		assertArrayEquals(Arrays.copyOfRange(resq, 0, 17), Arrays.copyOfRange(ans, 0, 17));
		assertArrayEquals(Arrays.copyOfRange(resq, 17, 29), Arrays.copyOfRange(ans, 17, 29));		
	}

}
