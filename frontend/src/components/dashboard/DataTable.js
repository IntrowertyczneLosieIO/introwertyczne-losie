import React from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import EditMajor from "../forms/EditMajor";
import EditRoom from "../forms/EditRoom";
import DeleteRoom from "../forms/DeleteRoom";
import DeleteMajor from "../forms/DeleteMajor";
import DeleteExam from "../forms/DeleteExam";
import ShowMajor from "../forms/ShowMajor";
import ShowRoom from "../forms/ShowRoom";
import ShowFaculty from "../forms/ShowFaculty"
import DeleteFaculty from "../forms/DeleteFaculty";

import EditExam from "../forms/EditExam";
import DeleteSubexam from "../forms/DeleteSubexam";
import ShowExam from "../forms/ShowExam";
import EditFaculty from "../forms/EditFaculty";

class DataTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            showEdit: {},
            showDelete: {},
            showInfo: {},
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

    setShowDelete = (index, show) => {
        let rest = this.state.showDelete;
        this.setState({
            showDelete: {
                ...rest,
                [index]: show
            }
        });
    }

    setShowInfo = (index, show) => {
        let rest = this.state.showInfo;
        this.setState({
            showInfo: {
                ...rest,
                [index]: show
            }
        });
    }


    handleShow = (index) => this.setShowEdit(index, true);
    handleHide = (index) => this.setShowEdit(index, false);

    handleShowDelete = (index) => this.setShowDelete(index, true);
    handleHideDelete = (index) => this.setShowDelete(index, false);

    handleShowInfo = (index) => this.setShowInfo(index, true);
    handleHideInfo = (index) => this.setShowInfo(index, false);

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
            "Exams": EditExam,
            "Subexams": DeleteSubexam,
            "SubexamsNonEditable": DeleteSubexam,
            "Faculties": EditFaculty
        };

        const nameComponentMappingDelete = {
            "Majors": DeleteMajor,
            "Rooms": DeleteRoom,
            "Exams": DeleteExam,
            "Subexams": DeleteSubexam,
            "SubexamsNonEditable": DeleteSubexam,
            "Faculties": DeleteFaculty
        };

        const nameComponentMappingShow = {
            "Majors": ShowMajor,
            "Rooms": ShowRoom,
            "Exams": ShowExam,
            "Subexams": DeleteSubexam,
            "SubexamsNonEditable": DeleteSubexam,
            "Faculties": ShowFaculty
        };
        const receivedToBeChanged = ["fullTime", "partTime", true, false];
        const receivedDate = ["startDate", "endDate"];

        const receiveDisplayedMapping = {
            "fullTime": "stacjonarne",
            "partTime": "niestacjonarne",
            true: "Tak",
            false: "Nie"
        }

        const FormToRender = nameComponentMapping[this.props.name];
        const FormToRenderDelete = nameComponentMappingDelete[this.props.name];
        const FormToRenderShow = nameComponentMappingShow[this.props.name];


        let rowList = this.state.tableData.map((row, rowIndex) => {
            let rowData = {};
            for (let i = 0; i < this.props.tableValues.length; i++) {
                rowData[this.props.tableValues[i]] = Object.values(row)[i] ?? "";
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
            let cellList = Object.values(row).map((columnValue, index) => {
                if (typeof (columnValue) === "object" && columnValue !== null) {
                    columnValue = columnValue.firstName + " " + columnValue.lastName;
                }
                let columnValueToDisplay;
                if (receivedToBeChanged.includes(columnValue)) {
                    columnValueToDisplay = receiveDisplayedMapping[columnValue]
                } else if (receivedDate.includes(Object.keys(row)[index])) {
                    columnValueToDisplay = (columnValue + "").slice(0, 10);
                } else {
                    columnValueToDisplay = columnValue;
                }
                return <td key={index} onDoubleClick={() => this.handleShowInfo(rowIndex)}>{columnValueToDisplay}
                    <FormToRenderShow handleShow={() => this.handleShowInfo(rowIndex)}
                                      handleHide={() => this.handleHideInfo(rowIndex)}
                                      show={this.state.showInfo[rowIndex]}
                                      initialInputValues={rowData}/>
                </td>
            })

            if (this.props.name === "Subexams") {
                return <tr>
                    {cellList}
                    <Button variant={"danger"} size={"sm"} onClick={() => this.handleShowDelete(rowIndex)}
                            block>Usuń</Button>
                    <FormToRenderDelete handleShow={() => this.handleShowDelete(rowIndex)}
                                        handleHide={() => this.handleHideDelete(rowIndex)}
                                        show={this.state.showDelete[rowIndex]}
                                        initialInputValues={rowData}/>
                </tr>
            } else if (this.props.name === "SubexamsNonEditable") {
                return <tr>
                    {cellList}
                </tr>
            } else {

                return <tr>
                    {cellList}
                    <th>
                        <Button variant={"info"} size={"sm"} onClick={() => this.handleShow(rowIndex)} block
                                className={"border border-light"}>Edytuj</Button>
                        <FormToRender handleShow={() => this.handleShow(rowIndex)}
                                      handleHide={() => this.handleHide(rowIndex)}
                                      show={this.state.showEdit[rowIndex]}
                                      initialInputValues={rowData}/>
                    </th>
                    <th>
                        <Button variant={"danger"} size={"sm"} onClick={() => this.handleShowDelete(rowIndex)}
                                block>Usuń</Button>
                        <FormToRenderDelete handleShow={() => this.handleShowDelete(rowIndex)}
                                            handleHide={() => this.handleHideDelete(rowIndex)}
                                            show={this.state.showDelete[rowIndex]}
                                            initialInputValues={rowData}/>
                    </th>
                </tr>
            }
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