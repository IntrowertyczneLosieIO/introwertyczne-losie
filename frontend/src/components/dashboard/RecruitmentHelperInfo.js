import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Alert from "react-bootstrap/Alert";
import Button from "react-bootstrap/Button";
import AddRecruitment from "../forms/AddRecruitment";
import EditRecruitment from "../forms/EditRecruitment";

class RecruitmentHelperInfo extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showAddNew: false
        }
    }

    handleShowAddNew = () => {
        if(this.state.showAddNew){
            this.setState({
                showAddNew: false
            });
        }
        else{
            this.setState({
                showAddNew: true
            });
        }
    }

    render() {

        return (
            <Row className={"mb-4"}>
                <Col xs={12}>
                    <Alert variant={"info"} className={"mt-3"}>
                        Aktualna Rekrutacja: <strong>{this.props.currentRecruitment}</strong>
                    </Alert>
                </Col>
                <Col xs={2}>
                    <Button variant={"outline-dark"} size={"sm"} block>Informacje o rekrutacji</Button>
                </Col>
                <Col xs={2}>
                    <Button variant={"success"} className={"mb-3"} size={"sm"} block onClick={()=>this.handleShowAddNew()}>Dodaj nową rekrutację</Button>
                    {this.state.showAddNew && <AddRecruitment> </AddRecruitment>}
                </Col>

                <Col xs={2}>
                    <Button variant={"info"} className={"mb-3"} size={"sm"} block onClick={()=>this.handleShowAddNew()}>Edytuj rekrutację</Button>
                    {this.state.showAddNew && <EditRecruitment> </EditRecruitment>}
                </Col>
            </Row>
        );
    }
}

export default RecruitmentHelperInfo;