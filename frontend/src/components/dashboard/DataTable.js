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

    componentDidMount() {
        fetch("/majors")
            .then((response) => {
                return response.json();
            })
            .then((response) => {
                this.setState({
                    tableData: [
                        {
                            firstColumn: response[0] ? response[0].fullName : "",
                            secondColumn: response[0] ? response[0].faculty : "",
                            thirdColumn: response[0] ? response[0].contactPerson1 : "",
                            fourthColumn: response[0] ? response[0].contactPerson2 : ""
                            },
                        {
                            firstColumn: response[1] ? response[1].fullName : "",
                            secondColumn: response[1] ? response[1].faculty : "",
                            thirdColumn: response[1] ? response[1].contactPerson1 : "",
                            fourthColumn: response[1] ? response[1].contactPerson2 : ""
                        }
                    ]
                });
                console.log(this.state.tableData);
            })
    }

    render() {
        let rowList = this.state.tableData.map((row) => {
            return <tr key={row.key}>
                <td>{row.firstColumn}</td>
                <td>{row.secondColumn}</td>
                <td>{row.thirdColumn}</td>
                <td>{row.fourthColumn}</td>
            </tr>
        })

        const {secondColumn, firstColumn, thirdColumn, fourthColumn} = this.props.tableHeader;
        return (
            <Table striped bordered hover size={"sm"}>
                <thead>
                <tr>
                    <th>{firstColumn}</th>
                    <th>{secondColumn}</th>
                    <th>{thirdColumn}</th>
                    <th>{fourthColumn}</th>
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