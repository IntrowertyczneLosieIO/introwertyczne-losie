import React from "react";
import Table from "react-bootstrap/Table";

class DataTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            tableData: []
        };
    }

    fetchMajors = () => {
        fetch("/newest-majors")
            .then((response) => {
                return response.json();
            })
            .then((response) => {
                let newTableData = response.map((responseValue, responseIndex) => {
                    let rows = {};
                    this.props.tableValues.forEach((tableValue, tableIndex) => {
                        rows["column" + (tableIndex + 1)] = response[responseIndex] ? response[responseIndex][tableValue] : "";
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
        if (this.props.name === "Majors") {
            this.fetchMajors();
        }
    }

    render() {
        let rowList = this.state.tableData.map((row) => {
            return <tr>
                <td>{row.column1}</td>
                <td>{row.column2}</td>
                <td>{row.column3}</td>
                <td>{row.column4}</td>
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