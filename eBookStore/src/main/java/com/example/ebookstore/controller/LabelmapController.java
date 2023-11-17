package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Labelmap;
import com.example.ebookstore.repository.LabelmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LabelmapController {
    @Autowired
    private LabelmapRepository labelmapRepository;

    // 初始化标签关系
    @GetMapping("/initLabelmap")
    public String initLabelmap() {
        labelmapRepository.deleteAll();

        Labelmap all = new Labelmap("所有书籍");

        Labelmap practical = new Labelmap("实用类");
        Labelmap programming = new Labelmap("编程");
        Labelmap primary = new Labelmap("中小学教辅");

        Labelmap literature = new Labelmap("文学类");
        Labelmap children = new Labelmap("儿童文学");
        Labelmap youth = new Labelmap("青春文学");
        Labelmap biography = new Labelmap("传记文学");
        Labelmap world = new Labelmap("世界名著");
        Labelmap chinese = new Labelmap("中国古典文学");
        Labelmap ancient = new Labelmap("古籍");

        Labelmap humanities = new Labelmap("人文社科类");
        Labelmap novel = new Labelmap("小说");
        Labelmap magic = new Labelmap("魔幻小说");
        Labelmap science = new Labelmap("科幻小说");
        Labelmap society = new Labelmap("社会小说");
        Labelmap martial = new Labelmap("武侠小说");
        Labelmap suspense = new Labelmap("悬疑/推理小说");

        practical.isSimilar(all);
        programming.isSimilar(practical);
        primary.isSimilar(practical);

        literature.isSimilar(all);
        children.isSimilar(literature);
        youth.isSimilar(literature);
        biography.isSimilar(literature);
        world.isSimilar(literature);
        chinese.isSimilar(literature);
        ancient.isSimilar(literature);

        humanities.isSimilar(all);
        novel.isSimilar(humanities);
        magic.isSimilar(humanities);
        science.isSimilar(humanities);
        society.isSimilar(humanities);
        martial.isSimilar(humanities);
        suspense.isSimilar(humanities);

        labelmapRepository.save(all);

        labelmapRepository.save(practical);
        labelmapRepository.save(programming);
        labelmapRepository.save(primary);

        labelmapRepository.save(literature);
        labelmapRepository.save(children);
        labelmapRepository.save(youth);
        labelmapRepository.save(biography);
        labelmapRepository.save(world);
        labelmapRepository.save(chinese);
        labelmapRepository.save(ancient);

        labelmapRepository.save(humanities);
        labelmapRepository.save(novel);
        labelmapRepository.save(magic);
        labelmapRepository.save(science);
        labelmapRepository.save(society);
        labelmapRepository.save(martial);
        labelmapRepository.save(suspense);

        return "success";
    }

    // 查询标签通过两跳可以到达的所有标签
    @GetMapping("/findSimilarLabelByName")
    public List<String> findSimilarLabelByName(@RequestParam(value = "name") String name) {
        List<Labelmap> labelmaps = labelmapRepository.findSimilarLabelByName(name);
        List<String> names = new java.util.ArrayList<>();
        for (Labelmap labelmap : labelmaps) {
            names.add(labelmap.getName());
        }
        return names;
    }
}
