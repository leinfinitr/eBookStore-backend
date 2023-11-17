package com.example.ebookstore.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Labelmap {
    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "SIMILAR")
    public Set<Labelmap> similar;
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private Labelmap() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Labelmap(String name) {
        this.name = name;
    }

    public void isSimilar(Labelmap labelmap) {
        if (similar == null)
            similar = new HashSet<>();
        similar.add(labelmap);
        if (labelmap.similar == null)
            labelmap.similar = new HashSet<>();
        labelmap.similar.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
