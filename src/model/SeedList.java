/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SEEDS")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedList {

    @XmlElement(name = "SEED")
    private List<Seed> seeds;


    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }



}
