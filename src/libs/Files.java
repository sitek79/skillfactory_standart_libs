package libs;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Files extends Reader{
    // создаем объект File для каталога
    static File dir1 = new File("G://prog/java/skillfactory_standart_libs/files");
    // создаем объекты для файлов, которые находятся в каталоге
    static File file1 = new File("G://prog/java/skillfactory_standart_libs/files", "letter.txt");
    static File file2 = new File(dir1, "encrypt.txt");

    public void files() throws NoSuchAlgorithmException, IOException, BcExampleException {
        // если объект представляет каталог
        if (dir1.isDirectory()) {
            if(file1.exists())
                System.out.println("letter.txt -> File exists");
            else
                System.out.println("letter.txt -> File not found");
            if(file2.exists())
                System.out.println("encrypt.txt -> File exists");
            else
                System.out.println("encrypt.txt -> File not found");
            // определим размер файлов
            System.out.println("file1 'letter.txt' size: " + file1.length());
            System.out.println("file2 'encrypt.txt' size: " + file2.length());
            // можем ли мы читать из файла источника
            if(file1.canRead())
                System.out.println("File1 'letter.txt' может быть прочитан");
            else
                System.out.println("File1 'letter.txt' не может быть прочитан");
            // можем ли мы писать в файл назначения
            if(file2.canWrite())
                System.out.println("File2 'encrypt.txt' может осуществляться запись");
            else
                System.out.println("File2 'encrypt.txt' не может осуществляться запись");
            // получаем все вложенные объекты в каталоге
            for (File item : dir1.listFiles()) {
                if (item.isDirectory()) {
                    System.out.println(item.getName() + "  \t это каталог");
                } else {
                    System.out.println(item.getName() + "\t это файл");
                }
            }
        }
        // почитаем из файла
        Reader read = new Reader();
        read.readFile("G://prog/java/skillfactory_standart_libs/files/letter.txt");

        // почитаем файл средствами Java
        // выполним чтение из одного и запись в другой файл:
        try (FileInputStream fIn=new FileInputStream("G://prog//java//skillfactory_standart_libs//files//letter.txt");
             FileOutputStream fOs=new FileOutputStream("G://prog//java//skillfactory_standart_libs//files//encrypt.txt"))
        {
            byte[] buffer = new byte[fIn.available()];
            // считываем буфер
            fIn.read(buffer, 0, buffer.length);
            // записываем из буфера в файл
            fOs.write(buffer, 0, buffer.length);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        //
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] aesKey = secretKey.getEncoded();
        byte[] hmacShaKey = "hmacShaKey".getBytes(StandardCharsets.UTF_8);
        byte[] message = "myMessage".getBytes(StandardCharsets.UTF_8);
        //
        //
        Crypter crypter = new Crypter(aesKey, hmacShaKey);
        ByteArrayOutputStream encryptedStream = new ByteArrayOutputStream();
        crypter.encrypt(new ByteArrayInputStream(message), encryptedStream);
        ByteArrayOutputStream decryptedStream = new ByteArrayOutputStream();
        crypter.decrypt(new ByteArrayInputStream(encryptedStream.toByteArray()), decryptedStream);
        System.out.println("Decrypted: " + new String(decryptedStream.toByteArray(), StandardCharsets.UTF_8));
    }
}