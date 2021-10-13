package engineer.trustmeimansoftware.algtheory.week02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class LatticePaths3DTest {

    @Test
    @DisplayName("calc euclidean distance from 3,-5,23 to 0,0,0")
    public void euclDistTo0() {
        LatticePaths3D l = new LatticePaths3D(100);
        float distance = l.euclideanDistance(new int[]{3, -5, 23});
        assertEquals(563, (int) distance);
    }

    @Test
    @DisplayName("calc paths from 0,1,2,3")
    public void paths0123() {
        assertEquals(1, new LatticePaths3D(0).paths().intValue());
        assertEquals(1, new LatticePaths3D(1).paths().intValue());
        assertEquals(25, new LatticePaths3D(2).paths().intValue());
        assertEquals(211, new LatticePaths3D(3).paths().intValue());
    }

    @Test
    @DisplayName("calc paths from 100")
    public void paths100() {
        LatticePaths3D lattice = new LatticePaths3D(100);
        BigInteger paths = lattice.paths();
        assertEquals("96289730300863202936400221976192896125164881136035630435853623956250284339471501014901825312252960400168899672077271671261668835457655955830665", paths.toString());
    }

}