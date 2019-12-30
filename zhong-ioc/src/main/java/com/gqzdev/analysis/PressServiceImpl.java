package com.gqzdev.analysis;

 /**
  * @description: 出版社类
  * @Author: ganquanzhong
  * @Date:  2019/12/30 9:42
  */
public class PressServiceImpl implements PressService {
    /**
     * 依赖BookService
     */
    private BookService bookService;

    /**
     * 依赖注入的地方
     * @param bookService
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String say() {
        return "本书的价格是：" + bookService.getBookPrice();
    }
}
