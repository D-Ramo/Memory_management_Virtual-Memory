/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author stman
 */
public class Address {

    private int physicalMemoryAddress;
    private int signedValue;
    private int logicalAddress;


    
    
    
    
    public Address(int signedValue,int physicalMemoryAddress,int logicalAddress){
        this.physicalMemoryAddress = physicalMemoryAddress;
        this.signedValue=signedValue;
        this.logicalAddress=logicalAddress;
    }
    /**
     * @return the physicalMemoryAddress
     */
    public int getPhysicalMemoryAddress() {
        return physicalMemoryAddress;
    }

    /**
     * @param physicalMemoryAddress the physicalMemoryAddress to set
     */
    public void setPhysicalMemoryAddress(int physicalMemoryAddress) {
        this.physicalMemoryAddress = physicalMemoryAddress;
    }

 

    /**
     * @return the signedValue
     */
    public int getSignedValue() {
        return signedValue;
    }

    /**
     * @param signedValue the signedValue to set
     */
    public void setSignedValue(int signedValue) {
        this.signedValue = signedValue;
    }

    /**
     * @return the logicalAddress
     */
    public int getLogicalAddress() {
        return logicalAddress;
    }

    /**
     * @param logicalAddress the logicalAddress to set
     */
    public void setLogicalAddress(int logicalAddress) {
        this.logicalAddress = logicalAddress;
    }


}
