package misuy.btree;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class BTreeTest {
    private BTree tree;

    @BeforeAll
    void setupTree() {
        this.tree = new BTree(2);
    }

    @BeforeEach
    void clearTree() {
        this.tree.clear();
    }

    @Test
    @DisplayName("check inserting")
    void inserting() {
        assertAll(
            () -> {
                this.tree.insert(Arrays.asList(3, 2, 1));
                assertArrayEquals(new Integer[]{1, 2, 3}, this.tree.toList().toArray());
                this.tree.clear();
            },
            () -> {
                this.tree.insert(Arrays.asList(2, 3, 5, 1, 4));
                assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, this.tree.toList().toArray());
                this.tree.clear();
            }
        );
    }

    @Test
    @DisplayName("check deleting")
    void deleting() {
        assertAll(
            () -> {
                this.tree.insert(Arrays.asList(3, 2, 1));
                this.tree.delete(Arrays.asList(1, 3));
                assertArrayEquals(new Integer[]{2}, this.tree.toList().toArray());
                this.tree.clear();
            },
            () -> {
                this.tree.insert(Arrays.asList(2, 3, 5, 1, 4));
                this.tree.delete(Arrays.asList(5, 4, 3, 2, 1));
                assertArrayEquals(new Integer[]{}, this.tree.toList().toArray());
                this.tree.clear();
            }
        );
    }

    @Test
    @DisplayName("check equal keys")
    void equalKeys() {
        assertAll(
            () -> {
                this.tree.insert(Arrays.asList(1, 1, 2));
                assertArrayEquals(new Integer[]{1, 1, 2}, this.tree.toList().toArray());
                this.tree.clear();
            },
            () -> {
                this.tree.insert(Arrays.asList(0, 0, 0, 0, 0));
                assertArrayEquals(new Integer[]{0, 0, 0, 0, 0}, this.tree.toList().toArray());
                this.tree.clear();
            }
        );
    }
}
