package br.com.zup.vivo.easy.da;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


public class AppTest2 {

    private static Storage storage;

    public static void main(String... args) throws Exception {

        System.out.println("Instantiate GS Service... ");
        storage = StorageOptions.getDefaultInstance().getService();
        System.out.println("Instantiates GS Service OK");

        Page<Blob> files = getFilesFromBucket(getBucket("meusrelatorios"));

        for(Blob file: files.getValues()){
            
            System.out.printf("%n --- %s --- %n", file.getName());
            byte[] content = file.getContent();
            String csv = new String(content);
            System.out.println(csv);

        }

        System.out.println("Done... ");

    }

    private static Bucket getBucket(String bucketName){
        return storage.get(bucketName);
    }

    private static Page<Blob>  getFilesFromBucket(Bucket bucket){
        
        return bucket.list(Storage.BlobListOption.currentDirectory());
    }

    private void getBucketList(){

        System.out.println("Retrieving buckets list from Google... ");
        Page<Bucket> bucketList = storage.list();
        System.out.println("Retrieving buckets list from Google... OK");

        for(Bucket bucket : bucketList.getValues()){
            System.out.printf("Bucket Name: %s %n", bucket.getName());
        }

    }
}
