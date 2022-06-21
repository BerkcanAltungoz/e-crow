import axios from "axios";

export default class ItemTransactionService{

    getAll() {
        axios.get("http://localhost:8080/api/itemTransaction/getAll")
    }

    getAllSortedByStatus(){
        axios.get("http://localhost:8080/api/itemTransaction/getAllSortedByStatus")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/itemTransaction/getById?id=${id}`)
    }

    getByBuyerId(buyerId){
        axios.get(`http://localhost:8080/api/itemTransaction/getByFkBuyerId?buyerId=${buyerId}`)
    }

    getByEmployeeId(employeeId){
        axios.get(`http://localhost:8080/api/itemTransaction/getByFkEmployeeId?employeeId=${employeeId}`)
    }

    getBySellerId(sellerId){
        axios.get(`http://localhost:8080/api/itemTransaction/getByFkSellerId?sellerId=${sellerId}`)
    }

    deleteById(id) {
        axios.delete(`http://localhost:8080/api/itemTransaction/deleteById?id=${id}`)
    }

    updateStatus(values){
        axios.patch("http://localhost:8080/api/itemTransaction/updateStatus", values)
    }
}