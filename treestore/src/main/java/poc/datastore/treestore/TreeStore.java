package poc.datastore.treestore;


import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.AuthLevel;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/
@Api(
        name = "treestore",
        version = "v1",
        namespace =
        @ApiNamespace(
                ownerDomain = "poc.datastore.treestore",
                ownerName = "poc.datastore.treestore",
                packagePath = "poc.datastore.treestore"
        )
)
public class TreeStore {
    @ApiMethod(name = "description", path="description",httpMethod = ApiMethod.HttpMethod.GET)
    public Message description() {
        return new Message("This is a framework to store tree-like hierarchical data");
    }
}
