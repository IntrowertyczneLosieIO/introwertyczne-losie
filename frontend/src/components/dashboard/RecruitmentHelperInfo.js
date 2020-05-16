import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Alert from "react-bootstrap/Alert";
import Button from "react-bootstrap/Button";
import AddRecruitment from "../forms/AddRecruitment";

class RecruitmentHelperInfo extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showAddNew: false
        }
    }

    setShowAddNew = (show) => {
        this.setState({
            showAddNew: show
        });
    }

    render() {

        return (
            <Row className={"mb-4"}>
                <Col xs={12}>
                    <Alert variant={"primary"} className={"mt-3"}>
                        Aktualna Rekrutacja: <strong>{this.props.currentRecruitment}</strong>
                    </Alert>
                </Col>
                <Col xs={2}>
                    <Button variant={"outline-primary"} size={"sm"} block>Informacje o rekrutacji</Button>
                </Col>
                <Col xs={2}>
                    <Button variant={"success"} className={"mb-3"} size={"sm"} block onClick={()=>this.setState({showAddNew:true})}>Dodaj nową rekrutację</Button>
                    {this.state.showAddNew && <AddRecruitment> </AddRecruitment>}
                </Col>
            </Row>
        );
    }
}

export default RecruitmentHelperInfo;