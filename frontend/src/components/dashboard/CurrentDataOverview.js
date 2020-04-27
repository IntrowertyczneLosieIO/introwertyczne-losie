import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import DataTable from "./DataTable";
import Button from "react-bootstrap/Button";
// import AddMajor from "../forms/AddMajor";

class CurrentDataOverview extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showAddNewMajor: false,
            Majors: "Kierunki",
            Rooms: "Sale",
            Exams: "Egzaminy"
        }
    }

    setShowAddNewMajor = (show) => {
        this.setState({
            showAddNewMajor: show
        });
    }

    handleShow = () => this.setShowAddNewMajor(true);
    handleHide = () => this.setShowAddNewMajor(false);

    render() {
        let data = {
            [this.state.Majors]: {
                firstColumn: "Nazwa",
                secondColumn: "Wydział",
                thirdColumn: "Kierunek łączony",
                fourthColumn: "Rodzaj studiów"
            },
            [this.state.Rooms]: {
                firstColumn: "Nazwa",
                secondColumn: "Nazwa",
                thirdColumn: "Nazwa",
                fourthColumn: "Nazwa"
            },
            [this.state.Exams]: {
                firstColumn: "Nazwa",
                secondColumn: "Nazwa",
                thirdColumn: "Nazwa",
                fourthColumn: "Nazwa"
            }
        }
        return (
            <div>
                <Row>
                    <Col><h4 className={"mb-3"}>{this.props.name}</h4></Col>
                </Row>
                <Row>
                    <Col>
                        <DataTable tableHeader={data[this.props.name]} name={this.props.name}/>
                    </Col>
                </Row>
                <Row className={"mb-4"}>
                    <Col xs={2}>
                        <Button variant={"outline-primary"} size={"sm"} block>{this.props.more}</Button>
                    </Col>
                    <Col xs={2}>
                        <Button variant={"success"} className={"mb-3"} size={"sm"} block
                                onClick={this.handleShow}>{this.props.addNew}</Button>
                        {/*<AddMajor*/}
                        {/*    handleShow={this.handleShow}*/}
                        {/*    handleHide={this.handleHide}*/}
                        {/*    show={this.state.showAddNewMajor}*/}
                        {/*    options={10}/>*/}
                    </Col>
                </Row>
            </div>
        );
    }
}

export default CurrentDataOverview;