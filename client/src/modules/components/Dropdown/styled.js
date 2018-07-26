import styled, {css} from 'styled-components';
import {Colors, themed, themeStyle} from 'modules/theme';

const PointerTop = css`
  top: 100%;
  left: 15px;
`;
const PointerBottom = css`
  bottom: 100%;
  right: 15px;
`;

const PointerBasics = css`
  position: absolute;
  border: solid transparent;
  content: ' ';
  pointer-events: none;
  ${({placement}) => (placement === 'top' ? PointerTop : PointerBottom)};
`;

const PointerBody = css`
  border-width: 7px;
  margin-right: -7px;
  border-bottom-color: ${themeStyle({
    dark: Colors.uiDark04,
    light: Colors.uiLight02
  })};
  ${({placement}) => (placement === 'top' ? 'transform: rotate(180deg)' : '')};
`;

const PointerShadow = css`
  border-width: 8px;
  margin-right: -8px;
  border-bottom-color: ${themeStyle({
    dark: Colors.uiDark06,
    light: Colors.uiLight05
  })};

  ${({placement}) => (placement === 'top' ? 'transform: rotate(180deg)' : '')};
`;

export const Label = styled.button`
  display: flex;
  align-items: center;

  border: none;
  outline: none;

  color: currentColor;
  background: none;

  font-family: IBMPlexSans;
  font-size: 15px;
  font-weight: 600;

  cursor: pointer;

  svg {
    vertical-align: text-bottom;
  }
`;

export const LabelWrapper = styled.div`
  margin-right: 8px;
`;

export const Dropdown = styled.div`
  display: inline-block;
  position: relative;
`;

export const DropdownMenu = themed(styled.div`
  /* Positioning */
  position: absolute;
  right: ${({placement}) => (placement === 'top' ? '-148px' : '-1px')};
  ${({placement}) => (placement === 'top' ? 'bottom: 25px;' : 'top:17px')};

  /* Display & Box Model */
  min-width: 186px;
  margin-top: 5px;
  box-shadow: 0 0 2px 0
    ${themeStyle({
      dark: 'rgba(0, 0, 0, 0.6)',
      light: ' rgba(0, 0, 0, 0.2)'
    })};
  border: 1px solid
    ${themeStyle({dark: Colors.uiDark06, light: Colors.uiLight05})};
  border-radius: 3px;

  /* Color */
  background-color: ${themeStyle({
    dark: Colors.uiDark04,
    light: Colors.uiLight02
  })};
  color: ${themeStyle({
    dark: '#ffffff',
    light: Colors.uiLight06
  })};

  /* Other */
  &:after,
  &:before {
    ${PointerBasics};
  }

  &:after {
    ${PointerBody};
  }
  &:before {
    ${PointerShadow};
  }
`);

const PointerBottomOptions = placement => {
  const bottom = css`
  &:first-child:hover:after, &:first-child:hover:before {
    ${PointerBasics};
    }
  
    &:first-child:hover:after, &:first-child:hover:before {
    ${PointerBasics};
    }
  
    &:first-child:hover:after{
      z-index: 1;
      ${PointerBody}
        border-bottom-color: ${themeStyle({
          dark: Colors.uiDark06,
          light: Colors.uiLight05
        })};
    }
  
    &:first-child:active:after{
        ${PointerBody}
        border-bottom-color: ${themeStyle({
          dark: Colors.darkActive,
          light: Colors.lightActive
        })};
    }
  `;

  const top = css`
&:last-child:hover:after, &:last-child:hover:before {
  ${PointerBasics};
  }

  &:last-child:hover:after, &:last-child:hover:before {
  ${PointerBasics};
  }

  &:last-child:hover:after{
    z-index: 1;
    ${PointerBody}
      border-bottom-color: ${themeStyle({
        dark: Colors.uiDark06,
        light: Colors.uiLight05
      })};
  }

  &:last-child:active:after{
      ${PointerBody}
      border-bottom-color: ${themeStyle({
        dark: Colors.darkActive,
        light: Colors.lightActive
      })};
  }
`;
  return placement === 'top' ? top : bottom;
};

export const Option = themed(styled.button`
  /* Positioning */
  height: 36px;

  /* Display & Box Model */
  display:flex;
  align-items: center;
  width: 100%;
  padding: 0 10px;
  border: none;
  outline: none;

  /* Color */
  color: currentColor;
  background: none;

  /* Text */
  text-align: left;
  font-size: 15px;
  font-weight: 600;
  line-height: 36px;

  /* Other */
  cursor: pointer;


  &:hover {
    background: ${themeStyle({
      dark: Colors.uiDark06,
      light: Colors.uiLight05
    })};
  }

  &:active {
    background: ${themeStyle({
      dark: Colors.darkActive,
      light: Colors.lightActive
    })};
  }
 
  ${({placement}) => PointerBottomOptions(placement)};


  /* Add Border between options */
  &:not(:last-child) {
    border-bottom: 1px solid ${themeStyle({
      dark: Colors.uiDark06,
      light: Colors.uiLight05
    })}
`);
