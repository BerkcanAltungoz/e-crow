import axios from "axios";

export default class ItemTransactionService{

    getAll() {
        return axios.get("http://localhost:8080/api/itemTransaction/getAll")
    }

    getAllSortedByStatus(){
        return axios.get("http://localhost:8080/api/itemTransaction/getAllSortedByStatus")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/itemTransaction/getById?id=${id}`)
    }

    existsByBuyerId(buyerId){
        return axios.get(`http://localhost:8080/api/itemTransaction/existsByFkBuyerId?buyerId=${buyerId}`)
    }

    existsByEmployeeId(employeeId){
        return axios.get(`http://localhost:8080/api/itemTransaction/existsByFkEmployeeId?employeeId=${employeeId}`)
    }

    existsBySellerId(sellerId){
        return axios.get(`http://localhost:8080/api/itemTransaction/existsByFkSellerId?sellerId=${sellerId}`)
    }

    getByBuyerId(buyerId){
        return axios.get(`http://localhost:8080/api/itemTransaction/getByFkBuyerId?buyerId=${buyerId}`)
    }

    getByEmployeeId(employeeId){
        return axios.get(`http://localhost:8080/api/itemTransaction/getByFkEmployeeId?employeeId=${employeeId}`)
    }

    getBySellerId(sellerId){
        return axios.get(`http://localhost:8080/api/itemTransaction/getByFkSellerId?sellerId=${sellerId}`)
    }

    deleteById(id) {
        return axios.delete(`http://localhost:8080/api/itemTransaction/deleteById?id=${id}`)
    }

    updateStatus(values){
        return axios.patch("http://localhost:8080/api/itemTransaction/updateStatus", values)
    }
}