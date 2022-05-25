import java.io.*;

/**
 * Rep the author, this is from the student repo -Nick Pelletier
 * @author Sean Grimes, sean@seanpgrimes.com
 *
 * Serialize and deserialize any type of object that implements the serializable
 * interface.
 */
public class SerializationHelper {

    /**
     * This is a generic deserialization method, it only needs to know the type of
     * object it is deserializing. It will work for *anything* that can be deserialized.
     * @param type The object type, passed as, for example, Object.class
     * @param path The path to the file to be deserialized
     * @param <T> A necessary generic qualifier, it is implicitly passed
     * @return The deserialized object
     * NOTE: This would be called like so:
     * SerializationHelper.deserialize(Object.class, path) where "Object.class" is
     * whatever object type you're deserializing, e.g. "vehicle.Car.class" to deserialize the vehicle.Car
     */
    public static <T> T deserialize(Class<T> type, String path){
        // Test to make sure the path exists and is a file
        File tst = new File(path);
        if(!tst.exists() || !tst.isFile())
            throw new IllegalArgumentException(path + " is invalid");

        // Create the generic object that will hold the deserialized object
        T deserializedObject = null;
        try{
            // Streams to read it in
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Read it in with the cast to specific type
            deserializedObject = type.cast(ois.readObject());

            // Cleanup
            ois.close();
            fis.close();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.exit(2);
        }
        return deserializedObject;
    }

    /**
     * Serialize a class to disk using generics and passed class attributes.
     * @param type The object type, passed as, for example, Object.class
     * @param obj The object to be saved, will be up-cast to the proper type before saving
     * @param dirPath The path to the directory to store the object
     * @param fileName The name of the file to serialize the object to
     * @param <T> A necessary generic qualifier, implicitly passed
     * @return The full path to the serialized object, stored on disk
     */
    public static <T> String serialize(Class<T> type, Object obj, String dirPath,
                                       String fileName){
        // Make sure the directory exists
        FileUtils.createDirectory(dirPath);

        String fullPath = dirPath + fileName;
        try{
            FileOutputStream fos = new FileOutputStream(fullPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(type.cast(obj));

            oos.close();
            fos.close();
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(2);
        }
        return fullPath;
    }
}
