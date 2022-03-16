/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.online.evemarketer;

import java.util.Date;

/**
 *
 * @author thyle
 */
public class Sell {
    private ForQuery forQuery;
    private Long volume;
    private Long wavg;
    private Long avg;
    private Long variance;
    private Long stdDev;
    private Long median;
    private Long fivePercent;
    private Long max;
    private Long Min;
    private Boolean highToLow;
    private Date generated;

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getWavg() {
        return wavg;
    }

    public void setWavg(Long wavg) {
        this.wavg = wavg;
    }

    public Long getAvg() {
        return avg;
    }

    public void setAvg(Long avg) {
        this.avg = avg;
    }

    public Long getVariance() {
        return variance;
    }

    public void setVariance(Long variance) {
        this.variance = variance;
    }

    public Long getStdDev() {
        return stdDev;
    }

    public void setStdDev(Long stdDev) {
        this.stdDev = stdDev;
    }

    public Long getMedian() {
        return median;
    }

    public void setMedian(Long median) {
        this.median = median;
    }

    public Long getFivePercent() {
        return fivePercent;
    }

    public void setFivePercent(Long fivePercent) {
        this.fivePercent = fivePercent;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return Min;
    }

    public void setMin(Long Min) {
        this.Min = Min;
    }

    public Boolean getHighToLow() {
        return highToLow;
    }

    public void setHighToLow(Boolean highToLow) {
        this.highToLow = highToLow;
    }

    public Date getGenerated() {
        return generated;
    }

    public void setGenerated(Date generated) {
        this.generated = generated;
    }

    public ForQuery getForQuery() {
        return forQuery;
    }

    public void setForQuery(ForQuery forQuery) {
        this.forQuery = forQuery;
    }
    
}
