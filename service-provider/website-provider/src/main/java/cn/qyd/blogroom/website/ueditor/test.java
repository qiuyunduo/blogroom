package cn.qyd.blogroom.website.ueditor;

import java.io.*;

/**
 * @Author qyd
 * @Date 19-3-22 下午2:27
 **/
public class test {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();

        try {
            System.out.println(new File("./").getParent());
            InputStreamReader reader = new InputStreamReader(new FileInputStream("config.json"), "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);
            String tmpContent = null;

            while((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();
        } catch (UnsupportedEncodingException var6) {
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( builder.toString());

    }
}
