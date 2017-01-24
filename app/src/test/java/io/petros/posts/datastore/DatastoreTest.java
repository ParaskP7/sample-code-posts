package io.petros.posts.datastore;

import org.junit.Before;
import org.junit.Test;

import io.petros.posts.GeneralTestHelper;

import static org.assertj.core.api.Assertions.assertThat;

public class DatastoreTest extends GeneralTestHelper {

    private Datastore datastore;

    @Before
    public void setUp() {
        setUpMocks();
        setUpDatastore();
    }

    private void setUpDatastore() {
        datastore = new Datastore(datastoreSaveActionsMock, datastoreAddActionsMock, datastoreGetActionsMock,
                datastoreUpdateActionsMock);
    }

    @Test
    public void saveTest() {
        assertThat(datastore.save()).isEqualTo(datastoreSaveActionsMock);
    }

    @Test
    public void addTest() {
        assertThat(datastore.add()).isEqualTo(datastoreAddActionsMock);
    }

    @Test
    public void getTest() {
        assertThat(datastore.get()).isEqualTo(datastoreGetActionsMock);
    }

    @Test
    public void updateTest() {
        assertThat(datastore.update()).isEqualTo(datastoreUpdateActionsMock);
    }

}
