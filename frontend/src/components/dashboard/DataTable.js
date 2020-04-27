import React from "react";
import Table from "react-bootstrap/Table";

class DataTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            tableData: [
                {
                    firstColumn: "kierunek1",
                    secondColumn: "wydział1",
                    thirdColumn: "TAK",
                    fourthColumn: "stacjonarne",
                    key: "1"
                },
                {
                    firstColumn: "kierunek2",
                    secondColumn: "wydział2",
                    thirdColumn: "TAK",
                    fourthColumn: "stacjonarne",
                    key: "2"
                }
            ]
        };
    }

    componentDidMount() {
        //TODO: getting data from database
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