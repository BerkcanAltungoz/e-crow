import axios from "axios";

export default class ChatMessageService{
    getAll(){
        axios.get("http://localhost:8080/api/chatMessage/getAll")
    }

    getById(id){
        axios.get(`http://localhost:8080/api/chatMessage/getById?id=${id}`)
    }

    getByCustomerId(customerId){
        axios.get(`http://localhost:8080/api/chatMessage/getByFkCustomerId?customerId=${customerId}`)
    }

    getByEmployeeId(employeeId){
        axios.get(`http://localhost:8080/api/chatMessage/getByFkEmployeeId?employeeId=${employeeId}`)
    }

    getByCustomerIdAndEmployeeId(customerId, employeeId){
        axios.get(`http://localhost:8080/api/chatMessage/getByFkCustomerIdAndFkEmployeeId?customerId=${customerId}&employeeId=${employeeId}`)
    }

    deleteById(id){
        axios.delete(`http://localhost:8080/api/chatMessage/deleteById?id=${id}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/chatMessage/add", values)
    }


}