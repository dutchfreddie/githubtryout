package httpbotexamples.chapter5;

import java.io.*;
class Base64OutputStream extends FilterOutputStream
{

    public Base64OutputStream(OutputStream out)
  {
    super(out);
  }

  /**
   * Write a byte to be encoded.
   *
   * @param c The character to be written.
   * @exception java.io.IOException
   */
  public void write(int c) throws IOException
  {
    buffer[index] = c;
    index++;
    if (index == 3)
    {
      super.write(toBase64[(buffer[0] & 0xfc) >> 2]);
      super.write(toBase64[((buffer[0] & 0x03) << 4)
          | ((buffer[1] & 0xf0) >> 4)]);
      super.write(toBase64[((buffer[1] & 0x0f) << 2)
          | ((buffer[2] & 0xc0) >> 6)]);
      super.write(toBase64[buffer[2] & 0x3f]);

      index = 0;
    }
  }

  /**
   * Ensure all bytes are written.
   *
   * @exception java.io.IOException
   */
  public void flush() throws IOException
  {
    if (index == 1)
    {
      super.write(toBase64[(buffer[2] & 0x3f) >> 2]);
      super.write(toBase64[(buffer[0] & 0x03) << 4]);
      super.write('=');
      super.write('=');
    } else if (index == 2)
    {
      super.write(toBase64[(buffer[0] & 0xfc) >> 2]);
      super.write(toBase64[((buffer[0] & 0x03) << 4)
          | ((buffer[1] & 0xf0) >> 4)]);
      super.write(toBase64[(buffer[1] & 0x0f) << 2]);
      super.write('=');
    }
  }

  /**
   * Allowable characters for base-64.
   */
  private static char[] toBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
      'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
      'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
      'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
      'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

  /**
   * Current index.
   */
  private int index = 0;

  /**
   * Outbound buffer.
   */
  private int buffer[] = new int[3];

}
