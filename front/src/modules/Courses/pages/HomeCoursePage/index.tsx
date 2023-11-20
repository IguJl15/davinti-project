import { Body } from '../../../../core/components/Body';
import { Button } from './components/Button';
import "./style.css"

function HomeCoursePage() {
  return (
    <Body>
      <div className="mainWrapper">
        <div className="section_1">
          <div className="sectionTitle">
            <div className='title'>
              <h2>CURSO DE VENDER CELTA</h2>
              <Button />
            </div>
            <p>
              Spener agon respektive gall fulfillment. Neodiktisk nende med önyrad tret facial
              recognition lebel såväl som teniledes, laras.{' '}
            </p>
          </div>
          <div className="sla"></div>
        </div>
        <div className="section_2"></div>
      </div>
    </Body>
  );
}

export { HomeCoursePage };
