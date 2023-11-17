package com.example.ebookstore.repository;

import com.example.ebookstore.entity.Labelmap;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelmapRepository extends Neo4jRepository<Labelmap, Long> {
    // 查询标签通过两跳可以到达的所有标签
    @Query(value = "MATCH (label:Labelmap) - [:SIMILAR*2]->(label2:Labelmap) WHERE label.name=$0 RETURN label2")
    List<Labelmap> findSimilarLabelByName(String name);
}
