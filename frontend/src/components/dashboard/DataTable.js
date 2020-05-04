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

    fetchData = () => {
        fetch(this.props.mapping)
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
        this.fetchData();
    }

    render() {
        let rowList = this.state.tableData.map((row) => {
            let cellList = Object.values(row).map((columnValue, index) => {
                return <td key={index}>{columnValue}</td>
            })
            return <tr>
                {cellList}
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