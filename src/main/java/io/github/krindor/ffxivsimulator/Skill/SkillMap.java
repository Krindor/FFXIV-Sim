package io.github.krindor.ffxivsimulator.Skill;

import io.github.krindor.ffxivsimulator.Interfaces.ISkill;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.util.List;
import java.util.TreeMap;

public class SkillMap {
    public TreeMap<String, ISkill> Skillmap(String jobName) {
        TreeMap<String, ISkill> treeMap = new TreeMap<>();
        /*
        Uses FastClasspathScanner to find the name of all classes in the selected job package
         */
        FastClasspathScanner classpathScanner = new FastClasspathScanner(jobName);

        ScanResult scanResult = classpathScanner.scan();

        List<String> classNames = scanResult.getNamesOfAllStandardClasses();
        //Iterates over the names found with the scanner and then uses reflection to create an instance of that class
        for (String name : classNames) {
            try {
                ISkill skill = (ISkill) Class.forName(name).newInstance();
                treeMap.put(skill.getName(), skill);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                System.out.println("sadface");
            }
        }
        return treeMap;
    }

}
