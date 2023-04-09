package edu.codebridge.user.util;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import edu.codebridge.user.config.StorageConfig;

public class BlobHelper {

    public static CloudBlobContainer getBlobContainer(String containerName, StorageConfig storageConfig)
    {
        try
        {
            String blobStorageConnectionString = String.format("DefaultEndpointsProtocol=%s;"
                            + "BlobEndpoint=%s;"
                            + "QueueEndpoint=%s;"
                            + "TableEndpoint=%s;"
                            + "AccountName=%s;"
                            + "AccountKey=%s",
                    storageConfig.getDefaultEndpointsProtocol(), storageConfig.getBlobEndpoint(),
                    storageConfig.getQueueEndpoint(), storageConfig.getTableEndpoint(),
                    storageConfig.getAccountName(), storageConfig.getAccountKey());

            CloudStorageAccount account = CloudStorageAccount.parse(blobStorageConnectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            CloudBlobContainer container = serviceClient.getContainerReference(containerName);

            // Create a permissions object.
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();

            // Include public access in the permissions object.
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
            // Set the permissions on the container.
            container.uploadPermissions(containerPermissions);
            container.createIfNotExists();
            return container;
        }
        catch(Exception e)
        {
            System.out.println(e);
            // 加载上传文件启动异常
            return null;
        }
    }
}