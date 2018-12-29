import React, { Component } from 'react';
import { Col, Grid, Row ,Well} from 'react-bootstrap';
import { NavMenu } from './NavMenu';

export class Layout extends Component {
  displayName = Layout.name

  render() {
    return (
      <Grid fluid>
        <Row>
          <Col sm={3}>
            <NavMenu />
          </Col>
          <Col sm={9} style={ { marginTop: 20, paddingLeft : 20, paddingRight : 20} }>
               {this.props.children}
          </Col>
        </Row>
      </Grid>
    );
  }
}
