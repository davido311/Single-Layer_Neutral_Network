import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    String dirName;
    ArrayList<Data> dataList = new ArrayList<>();
    HashMap<String,String> hashMap = new HashMap<>();

    public DataReader(String dirName) {
        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath(dirName);
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    String language = file.subpath(1, 2).toString();

                    if(!hashMap.containsKey(language)){
                        hashMap.put(language,Files.readAllLines(file,Charset.defaultCharset()).toString());
                    }
                    else {
                        hashMap.put(language,hashMap.get(language)+Files.readAllLines(file,Charset.defaultCharset()).toString());
                    }

                    return super.visitFile(file, attrs);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        hashMap.entrySet().stream().forEach(e-> dataList.add(new Data(e.getKey(),e.getValue())));
    }


    public List<Data> getList(){
        return  this.dataList;
    }

    public HashMap<String,String> getHashMap(){
        return this.hashMap;
    }

}
