/*
 * UnifiedDataObjectImlp.java
 *
 * Created on 3. June 2007, 23:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ujorm.implementation.bean;
import java.util.Date;

/**
 * A BEAN Object
 * @author Pavel Ponec
 */
public class BeanUjoImpl extends BeanUjo {
    
    /** (Long) */
    public static final BeanProperty<BeanUjoImpl,Long>    PRO_P0 = newKey("P0");
    /** (Integer) */
    public static final BeanProperty<BeanUjoImpl,Integer> PRO_P1 = newKey("P1");
    /** (String) */
    public static final BeanProperty<BeanUjoImpl,String>  PRO_P2 = newKey("P2");
    /** (Date) */
    public static final BeanProperty<BeanUjoImpl,Date>    PRO_P3 = newKey("P3");
    /** (Float) */
    public static final BeanProperty<BeanUjoImpl,Float>   PRO_P4 = newKey("P4");
    /** Verify unique constants */
    static{init(BeanUjoImpl.class,true);}
    
    // ------- STANDARD BEAN --------------------
    
    /** (Long) */
    protected long p0;
    /** (Integer) */
    protected Integer p1;
    /** (String) */
    protected String p2;
    /** (Date) */
    protected Date p3;
    /** (Float) */
    protected Float p4;

    
    /** Creates a new instance of UnifiedDataObjectImlp */
    public BeanUjoImpl() {
    }
    
    public long getP0() {
        return p0;
    }
    
    public void setP0(long p0) {
        this.p0 = p0;
    }
    
    
    public Integer getP1() {
        return p1;
    }
    
    public void setP1(Integer p1) {
        this.p1 = p1;
    }
    
    public String getP2() {
        return p2;
    }
    
    public void setP2(String p2) {
        this.p2 = p2;
    }
    
    public Date getP3() {
        return p3;
    }
    
    public void setP3(Date p3) {
        this.p3 = p3;
    }
    
    public Float getP4() {
        return p4;
    }
    
    public void setP4(Float p4) {
        this.p4 = p4;
    }
    
    
}
