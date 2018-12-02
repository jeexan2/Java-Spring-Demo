package com.sit.jbc.controller.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.Grade;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.service.hrm_admin.GradeService;
import com.sit.jbc.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 08-Oct-18.
 */
@RequestMapping(value = "/grade")
@Controller
public class GradeController {
    @Autowired
    GradeService gradeService;
    @RequestMapping(value="/page",method= RequestMethod.GET)
    public String getPage(Model model){
        List<Grade> grades = gradeService.findAll();
        model.addAttribute("grades", gradeService.findAll());
        Grade grade = gradeService.findByGradeId((long)2);
        grade.setIsActive(1L);
        return "hrm_admin/grade";
    }

    @RequestMapping(value="/page", method = RequestMethod.POST)
    public String addorUpdatePage(@ModelAttribute("grade") Grade grade, Model model){

        Date date = new Date();
        if(grade.getGradeId() == -1) {
            grade.setCreatedBy(1);
            grade.setCreatedOn(date);
            //grade.setIsActive(1);
            grade.setIsMigrated(1);
            grade.setIsDeleted(0);
            grade.setIsUpdated(0);
            grade.setUpdatedOn(date);
            gradeService.save(grade);
        } else {
            Grade oldgrade = gradeService.findByGradeId(grade.getGradeId());
            grade.setIsUpdated(1);
            grade.setUpdatedOn(date);
            grade.setUpdatedBy(1);
            grade.setIsMigrated(oldgrade.getIsMigrated());
            grade.setMigratedOn(oldgrade.getMigratedOn());
            grade.setCreatedBy(oldgrade.getCreatedBy());
            grade.setCreatedOn(oldgrade.getCreatedOn());
            gradeService.save(grade);
        }
        Grade grade1 = new Grade();
        model.addAttribute("grade",grade1);
        model.addAttribute("grades", gradeService.findAll());
        List<Grade> grades = gradeService.findAll();

        return "hrm_admin/grade";
    }


    /*
    [HttpPost]
        public ActionResult checkIfUSERNAMEExist(GEN_USER user)
        {

            int flag = 0;
            if (user.USER_NAME != null)
            {
                user.USER_NAME = user.USER_NAME.Trim().ToLower();

                List<GEN_USER> list = new List<GEN_USER>();
                list = obj.GetAll(user).ToList();
               // var searchedItem = from userTable in db.TRAINING_USER where userTable.USERNAME.ToUpper() == user.USERNAME.ToUpper() select userTable;
                if (list.Count() > 0)
                {
                    flag = 1;
                }
                else
                {
                    flag = 0;
                }
                // var model = new { param1 = my_string };
                return Json(flag, JsonRequestBehavior.AllowGet);
            }
            return Json(flag, JsonRequestBehavior.AllowGet);
        }
    */
    @RequestMapping(value="/getGrade/{id}",method = RequestMethod.GET)
    public @ResponseBody Grade getGrade(@PathVariable("id") long id){
        Grade grade = gradeService.findByGradeId(id);
        return grade;
    }

}


