package lt.webgallery.domain.image.convertImage;

import java.util.Base64;

public final class ConvertImage {

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    public static String encodeToString(byte[] byteArray) {

        return ENCODER.encodeToString(byteArray);
    }
}
