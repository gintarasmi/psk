package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.model.Show;
import vu.lt.mybatis.dao.ShowMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ShowsMyBatis {

    @Inject
    private ShowMapper showMapper;

    @Getter
    @Setter
    private Show showToCreate = new Show();

    @Getter
    private List<Show> allShows;

    @PostConstruct
    public void init(){
        loadAllShows();
    }

    @Transactional
    public String createShow(){
        this.showMapper.insert(showToCreate);
        return "/mybatis/shows?faces-redirect=true";
    }
    @Transactional
    public String deleteShow(Show showToDelete){
        this.showMapper.deleteByPrimaryKey(showToDelete.getId());
        return "/mybatis/shows?faces-redirect=true";
    }

    private void loadAllShows(){
        this.allShows = showMapper.selectAll();
    }
}
