package unittests.Renderer;

import Renderer.ImageWriter;
import org.junit.jupiter.api.Test;
import primitives.Color;


class ImageWriterTest {

    @Test
    void ImageWriter()
    {
        ImageWriter imageWriter = new ImageWriter("testYellow",800,500);
        for (int i = 0; i < 800; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                // 800/16 = 50- to color the grid in orange
                if (i % 50 == 0)
                {
                    imageWriter.writePixel(i, j, new Color(255,128,0));
                }
                // 500/10 = 50- to color the grid in orange
                else if (j % 50 == 0)
                {
                    imageWriter.writePixel(i, j, new Color(255,128,0));
                }
                else
                {
                    imageWriter.writePixel(i, j, new Color(255,255,0));
                }
            }
        }
        imageWriter.writeToImage();
    }
}
