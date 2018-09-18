package com.zhbit.cms.tools;

import com.zhbit.cms.infobeans.IdentifyingCodeInfo;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IdentifyingCodeTool {
    private Random rand = new Random();
    private char[] charArrays = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
    private BASE64Encoder encoder = new BASE64Encoder();
    private int lastIndex;

    private List<IdentifyingCodeInfo> codeInfoList;

    public IdentifyingCodeTool() {
        IdentifyingCodeInfo[] codeInfos = new IdentifyingCodeInfo[20];
        for (int i = 0; i < codeInfos.length; i++) {
            codeInfos[i] = new IdentifyingCodeInfo();
        }
        codeInfoList = Arrays.asList(codeInfos);
    }

    public IdentifyingCodeInfo getIdentifyingCode() {
        int item = rand.nextInt(20);
        while (item == lastIndex) {
            item = rand.nextInt(20);
        }
        lastIndex = item;
        IdentifyingCodeInfo codeInfo = codeInfoList.get(item);
        int count = codeInfo.getCount();
        if (count <= 0) {
            String[] data = createIdentifyingCode();
            codeInfo.reUser(data[0], data[1]);
        } else {
            codeInfo.incCount();
        }
        return codeInfo;
    }

    private String[] createIdentifyingCode() {
        BufferedImage bufferedImage = new BufferedImage(240, 60, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setFont(new Font("", rand.nextInt(4), 40 + rand.nextInt(8)));
        char result[] = new char[4];
        for (int i = 0; i <= 180; i += 60) {
            result[i / 60] = charArrays[rand.nextInt(36)];
            graphics2D.setColor(new Color(100 + rand.nextInt(155), 100 + rand.nextInt(155), 100 + rand.nextInt(155)));
            graphics2D.drawString(Character.toString(result[i / 60]), i + rand.nextInt(10), rand.nextInt(15) + 35);
        }
        for (int i = 0; i < 2; i++) {
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawLine(rand.nextInt(20), rand.nextInt(60), rand.nextInt(20) + 200, rand.nextInt(60));
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", baos);
            byte[] data = baos.toByteArray();
            return new String[]{String.valueOf(result), encoder.encode(data)};
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
