import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Alert from "react-bootstrap/Alert";
import Button from "react-bootstrap/Button";

class RecruitmentHelperInfo extends React.Component {
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
                    <Button variant={"success"} className={"mb-3"} size={"sm"} block>Dodaj nową rekrutację</Button>
                </Col>
            </Row>
        );
    }
}

export default RecruitmentHelperInfo;