package Demo.doMain;

import java.util.List;

public class PageBean <T>{
    private int pageCount;//总共多少页
    private int currPage;//当前第几页
    private int rows;//每页多少行
    private List<T> list;//list数据
    private int count;//总共多少条记录

    public PageBean() {
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageCount=" + pageCount +
                ", currPage=" + currPage +
                ", rows=" + rows +
                ", list=" + list +
                ", count=" + count +
                '}';
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
