package dao;

import java.util.List;

import bean.School;
import bean.Subject;

public interface SubjectDao {

    // 科目コードと学校から科目を取得する
    Subject get(String cd, School school);

    // 学校に関連付けられたすべての科目をフィルタリングする
    List<Subject>postFilter(school School) throws Exception {
    }
    
}
