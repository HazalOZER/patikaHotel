package business;

import dao.BookDao;
import entity.Book;
import entity.Hotel;

import java.util.ArrayList;

public class BookManager {
    private final BookDao bookDao;

    public BookManager() {
        this.bookDao = new BookDao();
    }

    public ArrayList<Book> selectByQuery(String query) {

        return this.bookDao.selectByQuery(query);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Book> bookList) {

        ArrayList<Object[]> bookObjList = new ArrayList<>();
        for (Book obj : bookList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoomId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getMpno();
            rowObject[i++] = obj.getTcno();
            rowObject[i++] = obj.getPension();
            rowObject[i++] = obj.getStartDate();
            rowObject[i++] = obj.getFinishDate();
            rowObject[i++] = obj.getAdult();
            rowObject[i++] = obj.getChild();
            rowObject[i++] = obj.getPrice();
            rowObject[i] = obj.getNote();

            bookObjList.add(rowObject);
        }
        return bookObjList;
    }

    public ArrayList<Book> findAll() {

        return this.bookDao.findAll();
    }

    public boolean save(Book book) {
        return this.bookDao.save(book);
    }

    public Book getById(int id) {
        return this.bookDao.getById(id);
    }
    public boolean delete(int id){
        return this.bookDao.delete(id);
    }
    public  boolean update(Book book){
        return this.bookDao.update(book);
    }
    public int getRoomIdByBookId(int bookId){
        return this.bookDao.getRoomIdByBookId(bookId);
    }
}
