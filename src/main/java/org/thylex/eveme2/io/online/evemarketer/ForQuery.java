/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.online.evemarketer;

/**
 *
 * @author thyle
 */
public class ForQuery {
    private String bid = "";
    private Long[] types;
    private Long[] regions;
    private Long[] systems;
    private Long hours;
    private Long minq;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Long[] getTypes() {
        return types;
    }

    public void setTypes(Long[] types) {
        this.types = types;
    }

    public Long[] getRegions() {
        return regions;
    }

    public void setRegions(Long[] regions) {
        this.regions = regions;
    }

    public Long[] getSystems() {
        return systems;
    }

    public void setSystems(Long[] systems) {
        this.systems = systems;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getMinq() {
        return minq;
    }

    public void setMinq(Long minq) {
        this.minq = minq;
    }
    
}
