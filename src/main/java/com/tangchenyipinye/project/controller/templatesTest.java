package com.tangchenyipinye.project.controller;

import com.tangchenyipinye.project.pojo.*;
import com.tangchenyipinye.project.service.*;
import com.tangchenyipinye.project.until.MD5until;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "templates测试")
@Controller
@RequestMapping("temapi")
public class templatesTest {
    @Autowired
    AdminService adminService;
    @Autowired
    ProductService productService;
    @Autowired
    UsersService usersService;
    @Autowired
    DataService dataService;
    @Autowired
    OrderService orderService;
    @Secured({"ROLE_admins"})
    @RequestMapping("/logout")
    public String logout() {
        return "success";
    }

//    //    管理员登录接口
//    @Secured({"ROLE_admins"})
//    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
//    public String adminRegister(@RequestParam("username") String username,
//                                @RequestParam("password") String password) {
//        System.out.println(username + "----" + password);
//        Admin admin = adminService.getUserByNameAndPass(username, password);
////        解密
//        String s = MD5until.string2MD5(password);
//        if (!s.equals(admin.getAdmin_password())) {
//            return "success";
//        }
//        return "redirect:../allProducts";
//    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admindb")
    public String admindbcontrol(Model model) {
            int i = 1;
        Data_status returndata=dataService.selectData_status(i);
        model.addAttribute("data",returndata);
            return "admin-db-control";
    }

//    //    进入商品添加页面
//    @Secured({"ROLE_admins"})
//    @RequestMapping("addProduct")
//    public String addProduct() {
//        return "addProduct";
//    }


//    //回到首页
//    @Secured({"ROLE_admins"})
//    @RequestMapping("/adminpage")
//    public String adminpage() {
//        return "admin-homepage";
//    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admincategory")
    public String admincategory() {
        return "admin-category-control";
    }


    @Secured({"ROLE_admins"})
    @RequestMapping("/adminorders")
    public String adminorders(Model model) {
        List<Order> ordersList = orderService.list();
        model.addAttribute("ordersList",ordersList);
        return "admin-orders-control";
    }
    //删除订单信息
    @Secured({"ROLE_admins"})
    @RequestMapping("/deleteOrderById")
    public String deleteOrderById(int id) {
        orderService.deleteOrderById(id);
        return "redirect:/temapi/adminorders";
    }
    @Secured("ROLE_admins")
    @RequestMapping(value = "/selectOrderByName",method = RequestMethod.POST)
    public String selectOrderByName(Model model,@RequestParam("title") String title){
        List list = orderService.selectOrderByName(title);
        model.addAttribute("ordersList", list);
        return "admin-orders-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/admingoods")
    public String admingoods() {
        return "admin-goods-control";
    }

    @Secured({"ROLE_admins"})
    @RequestMapping("/adminuser")
    public String adminuser() {
        return "admin-user-control";
    }



    //    管理员登录接口
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        System.out.println(username + "----" + password);
//        解密
        String s = MD5until.string2MD5(password);
        int i = adminService.getUserByAdminname(username, s);
        if(i==0){
            return "redirect:../../admin-login.html";
        }else{
            return "redirect:../allProducts";
        }
    }

    /*
     *********tangchenyipinye首页模块*********

     *********1.将商品信息发送到首页
     *********2.
     *********3.
     *********4.
    */

    /*
    将商品信息发送到首页
    */
    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye")
    public String tangchenyipinye(HttpServletRequest request){
        List<Product> productsList = productService.list();
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        request.setAttribute("productsList",productsList);
        return "tangchenyipinye";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye/shengcha")
    public String tangchenyipinyeshengcha(HttpServletRequest request){
        List<Product> productsList = productService.selectBycategory("生茶");
        request.setAttribute("productsList",productsList);
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        return "tangchenyipinye";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye/shucha")
    public String tangchenyipinyeshucha(HttpServletRequest request){
        List<Product> productsList = productService.selectBycategory("熟茶");
        request.setAttribute("productsList",productsList);
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        return "tangchenyipinye";
    }

    @Secured({"ROLE_admins"})
    @GetMapping("/tangchenyipinye/shengshu")
    public String tangchenyipinyeshengshu(HttpServletRequest request){
        List<Product> productsList = productService.selectBycategory("生熟套装");
        request.setAttribute("productsList",productsList);
        String username= usersService.getNameBySecurity();
        request.setAttribute("Username",username);
        return "tangchenyipinye";
    }

    /*
     *********商品信息管理模块*********

     *********1.获取所有商品信息
     *********2.通过id删除商品信息
     *********3.获取修改商品id
     *********4.修改商品
     *********5.添加商品
     *********6.商品模糊查询
     */
    /*
        获取商品信息发送到商品管理页面
        跳转到“商品管理页面”；
    */
    @Secured({"ROLE_admins"})
    @GetMapping("/allProducts")
    public String allProducts(Model model) {
        List<Product> productsList = productService.list();
        model.addAttribute("productsList", productsList);
        return "admin-goods-control";
    }

    /*
    通过id删除商品
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/deleteProductById")
    public String deleteProductById(int id) {
        productService.deleteProductById(id);
        return "redirect:../temapi/allProducts";
    }

    /*
        点击修改商品后，获取到商品id
        跳转到商品修改页面修改该商品
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/productToUpdate")
    public String toProductUpdate(Model model, int id) {
        Product product = productService.selectProductById(id);
        model.addAttribute("product", product);
        return "productUpdate";
    }

    /*
        取到商品id后，进入修改页面
        删除此id的商品
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/productUpdate")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/temapi/allProducts";
    }

    /*
        点击商品添加，进入商品添加页面，进行商品添加
        访问页面路径：http://localhost:8888/temapi/addProduct
        成功后重定向：http://localhost:8888/temapi/allProducts
    */
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/addProduct",method = RequestMethod.POST)
    public String addProduct(@RequestParam("title") String title,
                        @RequestParam("price") Integer price,
                        @RequestParam("category") String category,
                        @RequestParam("content") String content) {
        Product product = new Product(title,price,category,"http://localhost:8888/ref/img/c.jpg",10010,content);
        int i = productService.addProduct(product);
        if(i!=1){
            //添加商品失败 进入错误页面
        }
        return "redirect:/temapi/allProducts";
    }

    /*
        通过商品名称模糊查询商品信息
        将找到的结果返回到所有商品页面
        重定向：
    */
    @Secured("ROLE_admins")
    @RequestMapping(value = "/selectProductByName",method = RequestMethod.POST)
    public String selectProductByName(Model model,@RequestParam("title") String title){
        List list = productService.selectProductByName(title);
        model.addAttribute("productsList", list);
        return "admin-goods-control";
    }
    /*
                 *********用户信息管理模块*********

                 *********1.获取所有用户信息
                 *********2.通过id删除用户信息
                 *********3.获取修改用户id
                 *********4.修改用户
                 *********5.添加用户
                 *********6.用户模糊查询
     */

    /*
        获取用户信息发送到用户管理页面
        跳转到“用户管理页面”；
    */
    @Secured({"ROLE_admins"})
    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        List<Users> usersList = usersService.list();
        model.addAttribute("usersList", usersList);
        return "admin-user-control";
    }

    /*
        点击修改用户后，获取到用户id
        跳转到用户修改页面修改该用户信息
        用户修改页面：userUpdate.html
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/userToUpdate")
    public String toUserUpdate(Model model, int id) {
        Users users = usersService.selectUserById(id);
        model.addAttribute("users", users);
        return "userUpdate";
    }

    /*
        取到用户id后，进入修改页面
        修改此id的用户
        修改成功后回到商品管理页面
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/userUpdate")
    public String updateUser(Users users) {
        usersService.updateUser(users);
        return "redirect:/temapi/allUsers";
    }

    /*
        进入商品添加页面
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("toAddUser")
    public String toUser() {
        return "addUser";
    }

    /*
        点击商品添加，进入商品添加页面，进行商品添加
        访问页面路径：http://localhost:8888/temapi/addProduct
        成功后重定向：http://localhost:8888/temapi/allProducts
    */
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/addUser",method = RequestMethod.POST)
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("nickname") String nickname,
                          @RequestParam("mobile") String mobile,
                          @RequestParam("address") String address,
                          @RequestParam("email") String email) {
        Users users = new Users();
        users.setUsername(username);
        users.setNickname(nickname);
        users.setMobile(mobile);
        users.setAddress(address);
        users.setEmail(email);
        int i = usersService.addUser(users);
        if(i!=1){
            //添加商品失败 进入错误页面
        }
        return "redirect:/temapi/allUsers";
    }

    /*
        通过id删除用户
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/deleteUserById")
    public String deleteUserById(int id) {
        usersService.deleteUserById(id);
        return "redirect:/temapi/allUsers";
    }

    /*
        通过用户名称模糊查询用户信息
        将找到的结果返回到所有用户页面
        重定向：
    */
    @Secured("ROLE_admins")
    @RequestMapping(value = "/selectUserByName",method = RequestMethod.POST)
    public String selectUserByName(Model model,@RequestParam("username") String username){
        List list = usersService.selectUserByName(username);
        model.addAttribute("usersList", list);
        return "admin-user-control";
    }

    /*
             *********管理员信息管理模块*********

             *********1.获取所有管理员信息
             *********2.通过id删除管理员信息
             *********3.获取修改管理员id
             *********4.修改管理员
             *********5.添加管理员
             *********6.管理员模糊查询
     */

    /*
        1.获取所有管理员信息
        并发布到admin_admin_control
    */
    @Secured("ROLE_admins")
    @GetMapping(value = "/adminadmin")
    public String allAdmin(Model model){
        List<Admin> list = adminService.list();
        System.out.println(list);
        model.addAttribute("adminList",list);
        return "admin-admin-control";
    }

    /*
        3.获取修改管理员id
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/adminToUpdate")
    public String toAdminUpdate(Model model, int id) {
        Admin admin = adminService.selectAdminById(id);
        model.addAttribute("admin", admin);
        return "adminUpdate";
    }

    /*
        4.修改管理员
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/adminUpdate")
    public String updateAdmin(Admin admin) {
        System.out.println(admin);
        String s = admin.getAdminPassword();
        String s1 = MD5until.string2MD5(s);
        admin.setAdminPassword(s1);
        adminService.updateAdmin(admin);
        return "redirect:/temapi/adminadmin";
    }

    /*
        进入管理员添加页面
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("toAddAdmin")
    public String toAddAdmin() {
        return "addAdmin";
    }

    /*
        5.添加管理员
    */
    @Secured({"ROLE_admins"})
    @RequestMapping(value = "/admin/addAdmin",method = RequestMethod.POST)
    public String addUser(@RequestParam("adminName") String adminName,
                          @RequestParam("nickname") String nickname,
                          @RequestParam("password") String password) {
        System.out.println(password);
        String s = MD5until.string2MD5(password);
        Admin admin = new Admin(adminName,nickname,s,"http://localhost:8888/ref/img/a.jpg");
        int i = adminService.addAdmin(admin);
        if(i!=1){
            //添加商品失败 进入错误页面
        }
        return "redirect:/temapi/adminadmin";
    }

    /*
        2.通过id删除管理员信息
    */
    @Secured({"ROLE_admins"})
    @RequestMapping("/deleteAdminById")
    public String deleteAdminById(int id) {
        adminService.deleteAdminById(id);
        return "redirect:/temapi/adminadmin";
    }

    /*
        6.管理员模糊查询
    */
    @Secured("ROLE_admins")
    @RequestMapping(value = "/selectAdminByName",method = RequestMethod.POST)
    public String selectAdminByName(Model model,@RequestParam("adminName") String username){
        List list = adminService.selectAdminByName(username);
        model.addAttribute("adminList", list);
        return "admin-admin-control";
    }
}

