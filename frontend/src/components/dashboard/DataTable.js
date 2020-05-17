import React from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import EditMajor from "../forms/EditMajor";
import EditRoom from "../forms/EditRoom";

class DataTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            showEdit: {},
            tableData: []
        };
    }

    setShowEdit = (index, show) => {
        let rest = this.state.showEdit;
        this.setState({
            showEdit: {
                ...rest,
                [index]: show
            }
        });
    }

    handleShow = (index) => this.setShowEdit(index, true);
    handleHide = (index) => this.setShowEdit(index, false);

    fetchData = () => {
        fetch(this.props.mapping)
            .then((response) => {
                return response.json();
            })
            .then((response) => {
                let newTableData = response.map((responseValue, responseIndex) => {
                    let rows = {};
                    this.props.tableValues.forEach((tableValue, tableIndex) => {
                        rows[tableValue] = response[responseIndex] ? response[responseIndex][tableValue] : "";
                    })

                    return rows;
                })
                this.setState({
                    tableData: newTableData
                });
                console.log(this.state.tableData);
            })
    }

    componentDidMount() {
        this.fetchData();
    }

    render() {
        const nameComponentMapping = {
            "Majors": EditMajor,
            "Rooms": EditRoom,
            "Exams": EditRoom
        };
        const FormToRender = nameComponentMapping[this.props.name];

        let rowList = this.state.tableData.map((row, rowIndex) => {
            let cellList = Object.values(row).map((columnValue, index) => {
                if(typeof(columnValue) === "object" && columnValue !== null) {
                    columnValue = columnValue.firstName + " " + columnValue.lastName;
                }
                return <td key={index}>{columnValue}</td>
            })
            let rowData = {};
            for (let i=0; i<this.props.tableValues.length; i++) {
                rowData[this.props.tableValues[i]] = Object.values(row)[i] ? Object.values(row)[i] : "";
            }
            if (this.props.name === "Majors") {
                rowData.name1 = row.contactPerson1.firstName;
                rowData.surname1 = row.contactPerson1.lastName;
                rowData.phone1 = row.contactPerson1.phoneNo;
                rowData.email1 = row.contactPerson1.mail;
                if (row.contactPerson2 !== null) {
                    rowData.name2 = row.contactPerson2.firstName;
                    rowData.surname2 = row.contactPerson2.lastName;
                    rowData.phone2 = row.contactPerson2.phoneNo;
                    rowData.email2 = row.contactPerson2.mail;
                }
                delete rowData.contactPerson1;
                delete rowData.contactPerson2;
            }

            return <tr>
                {cellList}
                <Button variant={"info"} size={"sm"} onClick={() => this.handleShow(rowIndex)} block>Edytuj</Button>
                <FormToRender handleShow={() => this.handleShow(rowIndex)}
                              handleHide={() => this.handleHide(rowIndex)}
                              show={this.state.showEdit[rowIndex]}
                              initialInputValues={rowData}/>
            </tr>
        })

        let thList = this.props.tableHeader.map((value, index) => {
            return <th key={index}>{value}</th>
        })
        return (
            <Table striped bordered hover size={"sm"}>
                <thead>
                <tr>
                    {thList}
                </tr>
                </thead>
                <tbody>
                {rowList}
                </tbody>
            </Table>
        );
    }
}

export default DataTable;